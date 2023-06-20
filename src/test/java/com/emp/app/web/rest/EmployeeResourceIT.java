package com.emp.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.emp.app.IntegrationTest;
import com.emp.app.domain.Employee;
import com.emp.app.domain.enumeration.gender;
import com.emp.app.domain.enumeration.maritalStatus;
import com.emp.app.repository.EmployeeRepository;
import com.emp.app.service.dto.EmployeeDTO;
import com.emp.app.service.mapper.EmployeeMapper;
import jakarta.persistence.EntityManager;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EmployeeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EmployeeResourceIT {

    private static final String DEFAULT_EMP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMP_NAME = "BBBBBBBBBB";

    private static final Float DEFAULT_EMP_AGE = 1F;
    private static final Float UPDATED_EMP_AGE = 2F;

    private static final String DEFAULT_EMP_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_EMP_MOBILE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EMP_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EMP_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final gender DEFAULT_EMP_GENDER = gender.MALE;
    private static final gender UPDATED_EMP_GENDER = gender.FEMALE;

    private static final Boolean DEFAULT_EMP_PERSONAL_BUSINESS = false;
    private static final Boolean UPDATED_EMP_PERSONAL_BUSINESS = true;

    private static final maritalStatus DEFAULT_EMP_MARITAL_STATUS = maritalStatus.MARRIED;
    private static final maritalStatus UPDATED_EMP_MARITAL_STATUS = maritalStatus.UNMARRIED;

    private static final Duration DEFAULT_EMP_EXPERIENCE = Duration.ofHours(6);
    private static final Duration UPDATED_EMP_EXPERIENCE = Duration.ofHours(12);

    private static final String DEFAULT_EMP_NATIONALITY = "AAAAAAAAAA";
    private static final String UPDATED_EMP_NATIONALITY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/employees";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmployeeMockMvc;

    private Employee employee;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Employee createEntity(EntityManager em) {
        Employee employee = new Employee()
            .empName(DEFAULT_EMP_NAME)
            .empAge(DEFAULT_EMP_AGE)
            .empMobile(DEFAULT_EMP_MOBILE)
            .empBirthDate(DEFAULT_EMP_BIRTH_DATE)
            .empGender(DEFAULT_EMP_GENDER)
            .empPersonalBusiness(DEFAULT_EMP_PERSONAL_BUSINESS)
            .empMaritalStatus(DEFAULT_EMP_MARITAL_STATUS)
            .empExperience(DEFAULT_EMP_EXPERIENCE)
            .empNationality(DEFAULT_EMP_NATIONALITY);
        return employee;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Employee createUpdatedEntity(EntityManager em) {
        Employee employee = new Employee()
            .empName(UPDATED_EMP_NAME)
            .empAge(UPDATED_EMP_AGE)
            .empMobile(UPDATED_EMP_MOBILE)
            .empBirthDate(UPDATED_EMP_BIRTH_DATE)
            .empGender(UPDATED_EMP_GENDER)
            .empPersonalBusiness(UPDATED_EMP_PERSONAL_BUSINESS)
            .empMaritalStatus(UPDATED_EMP_MARITAL_STATUS)
            .empExperience(UPDATED_EMP_EXPERIENCE)
            .empNationality(UPDATED_EMP_NATIONALITY);
        return employee;
    }

    @BeforeEach
    public void initTest() {
        employee = createEntity(em);
    }

    @Test
    @Transactional
    void createEmployee() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();
        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);
        restEmployeeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isCreated());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate + 1);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getEmpName()).isEqualTo(DEFAULT_EMP_NAME);
        assertThat(testEmployee.getEmpAge()).isEqualTo(DEFAULT_EMP_AGE);
        assertThat(testEmployee.getEmpMobile()).isEqualTo(DEFAULT_EMP_MOBILE);
        assertThat(testEmployee.getEmpBirthDate()).isEqualTo(DEFAULT_EMP_BIRTH_DATE);
        assertThat(testEmployee.getEmpGender()).isEqualTo(DEFAULT_EMP_GENDER);
        assertThat(testEmployee.getEmpPersonalBusiness()).isEqualTo(DEFAULT_EMP_PERSONAL_BUSINESS);
        assertThat(testEmployee.getEmpMaritalStatus()).isEqualTo(DEFAULT_EMP_MARITAL_STATUS);
        assertThat(testEmployee.getEmpExperience()).isEqualTo(DEFAULT_EMP_EXPERIENCE);
        assertThat(testEmployee.getEmpNationality()).isEqualTo(DEFAULT_EMP_NATIONALITY);
    }

    @Test
    @Transactional
    void createEmployeeWithExistingId() throws Exception {
        // Create the Employee with an existing ID
        employee.setId(1L);
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEmployees() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList
        restEmployeeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId().intValue())))
            .andExpect(jsonPath("$.[*].empName").value(hasItem(DEFAULT_EMP_NAME)))
            .andExpect(jsonPath("$.[*].empAge").value(hasItem(DEFAULT_EMP_AGE.doubleValue())))
            .andExpect(jsonPath("$.[*].empMobile").value(hasItem(DEFAULT_EMP_MOBILE)))
            .andExpect(jsonPath("$.[*].empBirthDate").value(hasItem(DEFAULT_EMP_BIRTH_DATE.toString())))
            .andExpect(jsonPath("$.[*].empGender").value(hasItem(DEFAULT_EMP_GENDER.toString())))
            .andExpect(jsonPath("$.[*].empPersonalBusiness").value(hasItem(DEFAULT_EMP_PERSONAL_BUSINESS.booleanValue())))
            .andExpect(jsonPath("$.[*].empMaritalStatus").value(hasItem(DEFAULT_EMP_MARITAL_STATUS.toString())))
            .andExpect(jsonPath("$.[*].empExperience").value(hasItem(DEFAULT_EMP_EXPERIENCE.toString())))
            .andExpect(jsonPath("$.[*].empNationality").value(hasItem(DEFAULT_EMP_NATIONALITY)));
    }

    @Test
    @Transactional
    void getEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get the employee
        restEmployeeMockMvc
            .perform(get(ENTITY_API_URL_ID, employee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(employee.getId().intValue()))
            .andExpect(jsonPath("$.empName").value(DEFAULT_EMP_NAME))
            .andExpect(jsonPath("$.empAge").value(DEFAULT_EMP_AGE.doubleValue()))
            .andExpect(jsonPath("$.empMobile").value(DEFAULT_EMP_MOBILE))
            .andExpect(jsonPath("$.empBirthDate").value(DEFAULT_EMP_BIRTH_DATE.toString()))
            .andExpect(jsonPath("$.empGender").value(DEFAULT_EMP_GENDER.toString()))
            .andExpect(jsonPath("$.empPersonalBusiness").value(DEFAULT_EMP_PERSONAL_BUSINESS.booleanValue()))
            .andExpect(jsonPath("$.empMaritalStatus").value(DEFAULT_EMP_MARITAL_STATUS.toString()))
            .andExpect(jsonPath("$.empExperience").value(DEFAULT_EMP_EXPERIENCE.toString()))
            .andExpect(jsonPath("$.empNationality").value(DEFAULT_EMP_NATIONALITY));
    }

    @Test
    @Transactional
    void getNonExistingEmployee() throws Exception {
        // Get the employee
        restEmployeeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Update the employee
        Employee updatedEmployee = employeeRepository.findById(employee.getId()).get();
        // Disconnect from session so that the updates on updatedEmployee are not directly saved in db
        em.detach(updatedEmployee);
        updatedEmployee
            .empName(UPDATED_EMP_NAME)
            .empAge(UPDATED_EMP_AGE)
            .empMobile(UPDATED_EMP_MOBILE)
            .empBirthDate(UPDATED_EMP_BIRTH_DATE)
            .empGender(UPDATED_EMP_GENDER)
            .empPersonalBusiness(UPDATED_EMP_PERSONAL_BUSINESS)
            .empMaritalStatus(UPDATED_EMP_MARITAL_STATUS)
            .empExperience(UPDATED_EMP_EXPERIENCE)
            .empNationality(UPDATED_EMP_NATIONALITY);
        EmployeeDTO employeeDTO = employeeMapper.toDto(updatedEmployee);

        restEmployeeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, employeeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(employeeDTO))
            )
            .andExpect(status().isOk());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getEmpName()).isEqualTo(UPDATED_EMP_NAME);
        assertThat(testEmployee.getEmpAge()).isEqualTo(UPDATED_EMP_AGE);
        assertThat(testEmployee.getEmpMobile()).isEqualTo(UPDATED_EMP_MOBILE);
        assertThat(testEmployee.getEmpBirthDate()).isEqualTo(UPDATED_EMP_BIRTH_DATE);
        assertThat(testEmployee.getEmpGender()).isEqualTo(UPDATED_EMP_GENDER);
        assertThat(testEmployee.getEmpPersonalBusiness()).isEqualTo(UPDATED_EMP_PERSONAL_BUSINESS);
        assertThat(testEmployee.getEmpMaritalStatus()).isEqualTo(UPDATED_EMP_MARITAL_STATUS);
        assertThat(testEmployee.getEmpExperience()).isEqualTo(UPDATED_EMP_EXPERIENCE);
        assertThat(testEmployee.getEmpNationality()).isEqualTo(UPDATED_EMP_NATIONALITY);
    }

    @Test
    @Transactional
    void putNonExistingEmployee() throws Exception {
        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();
        employee.setId(count.incrementAndGet());

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmployeeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, employeeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(employeeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEmployee() throws Exception {
        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();
        employee.setId(count.incrementAndGet());

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmployeeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(employeeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEmployee() throws Exception {
        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();
        employee.setId(count.incrementAndGet());

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmployeeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEmployeeWithPatch() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Update the employee using partial update
        Employee partialUpdatedEmployee = new Employee();
        partialUpdatedEmployee.setId(employee.getId());

        partialUpdatedEmployee
            .empName(UPDATED_EMP_NAME)
            .empAge(UPDATED_EMP_AGE)
            .empMobile(UPDATED_EMP_MOBILE)
            .empGender(UPDATED_EMP_GENDER)
            .empMaritalStatus(UPDATED_EMP_MARITAL_STATUS)
            .empNationality(UPDATED_EMP_NATIONALITY);

        restEmployeeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmployee.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEmployee))
            )
            .andExpect(status().isOk());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getEmpName()).isEqualTo(UPDATED_EMP_NAME);
        assertThat(testEmployee.getEmpAge()).isEqualTo(UPDATED_EMP_AGE);
        assertThat(testEmployee.getEmpMobile()).isEqualTo(UPDATED_EMP_MOBILE);
        assertThat(testEmployee.getEmpBirthDate()).isEqualTo(DEFAULT_EMP_BIRTH_DATE);
        assertThat(testEmployee.getEmpGender()).isEqualTo(UPDATED_EMP_GENDER);
        assertThat(testEmployee.getEmpPersonalBusiness()).isEqualTo(DEFAULT_EMP_PERSONAL_BUSINESS);
        assertThat(testEmployee.getEmpMaritalStatus()).isEqualTo(UPDATED_EMP_MARITAL_STATUS);
        assertThat(testEmployee.getEmpExperience()).isEqualTo(DEFAULT_EMP_EXPERIENCE);
        assertThat(testEmployee.getEmpNationality()).isEqualTo(UPDATED_EMP_NATIONALITY);
    }

    @Test
    @Transactional
    void fullUpdateEmployeeWithPatch() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Update the employee using partial update
        Employee partialUpdatedEmployee = new Employee();
        partialUpdatedEmployee.setId(employee.getId());

        partialUpdatedEmployee
            .empName(UPDATED_EMP_NAME)
            .empAge(UPDATED_EMP_AGE)
            .empMobile(UPDATED_EMP_MOBILE)
            .empBirthDate(UPDATED_EMP_BIRTH_DATE)
            .empGender(UPDATED_EMP_GENDER)
            .empPersonalBusiness(UPDATED_EMP_PERSONAL_BUSINESS)
            .empMaritalStatus(UPDATED_EMP_MARITAL_STATUS)
            .empExperience(UPDATED_EMP_EXPERIENCE)
            .empNationality(UPDATED_EMP_NATIONALITY);

        restEmployeeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmployee.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEmployee))
            )
            .andExpect(status().isOk());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getEmpName()).isEqualTo(UPDATED_EMP_NAME);
        assertThat(testEmployee.getEmpAge()).isEqualTo(UPDATED_EMP_AGE);
        assertThat(testEmployee.getEmpMobile()).isEqualTo(UPDATED_EMP_MOBILE);
        assertThat(testEmployee.getEmpBirthDate()).isEqualTo(UPDATED_EMP_BIRTH_DATE);
        assertThat(testEmployee.getEmpGender()).isEqualTo(UPDATED_EMP_GENDER);
        assertThat(testEmployee.getEmpPersonalBusiness()).isEqualTo(UPDATED_EMP_PERSONAL_BUSINESS);
        assertThat(testEmployee.getEmpMaritalStatus()).isEqualTo(UPDATED_EMP_MARITAL_STATUS);
        assertThat(testEmployee.getEmpExperience()).isEqualTo(UPDATED_EMP_EXPERIENCE);
        assertThat(testEmployee.getEmpNationality()).isEqualTo(UPDATED_EMP_NATIONALITY);
    }

    @Test
    @Transactional
    void patchNonExistingEmployee() throws Exception {
        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();
        employee.setId(count.incrementAndGet());

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmployeeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, employeeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(employeeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEmployee() throws Exception {
        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();
        employee.setId(count.incrementAndGet());

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmployeeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(employeeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEmployee() throws Exception {
        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();
        employee.setId(count.incrementAndGet());

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmployeeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(employeeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeDelete = employeeRepository.findAll().size();

        // Delete the employee
        restEmployeeMockMvc
            .perform(delete(ENTITY_API_URL_ID, employee.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

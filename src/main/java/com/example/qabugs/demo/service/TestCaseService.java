package com.example.qabugs.demo.service;

import com.example.qabugs.demo.model.TestCase;
import com.example.qabugs.demo.repository.TestCaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
public class TestCaseService implements ITestCaseService {

    private TestCaseRepository testCaseRepository;

    public String createNothing(String something) {

        return String.format("Something is %s", something);

    }


    @Override
    public TestCase createTest(TestCase testCase) {

        TestCase newTestCase = testCaseRepository.save(testCase);

        return newTestCase;

    }

    @Override
    public TestCase getTestByID(Long id) {

        TestCase testCase = testCaseRepository.getById(id);

        return testCase;

    }

    @Override
    public List<TestCase> getTests() {

        List<TestCase> newTestCases = testCaseRepository.findAll();

        return newTestCases;

    }

    @Override
    public TestCase updateTestByID(Long id) {
        return null;
    }

    @Override
    public void deleteTestByID(Long id) {

        testCaseRepository.deleteById(id);

    }

    @Override
    public List<TestCase> getTestsByDateAfter(Date date) {
        return null;
    }
}

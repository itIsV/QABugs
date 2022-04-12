package com.example.qabugs.demo.service;

import com.example.qabugs.demo.model.TestCase;

import java.util.Date;
import java.util.List;

public interface ITestCaseService {

    public TestCase createTest(TestCase testCase);
    public TestCase getTestByID(Long id);
    public List<TestCase> getTests();
    public TestCase updateTestByID(Long id);
    public void deleteTestByID(Long id);
    public List<TestCase> getTestsByDateAfter(Date date);

}


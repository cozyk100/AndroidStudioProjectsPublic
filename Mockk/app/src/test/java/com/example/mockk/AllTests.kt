package com.example.mockk

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    AnnotationsTest::class,
    BasicDslTest::class,
    CaptureMockTest::class,
    ChainedCallsTest::class,
    ClassMockTest::class,
    ConstractorMock2Test::class,
    ConstractorMockTest::class,
    EnumerationMockTest::class,
    ExtendFunctionTest::class,
    ExtendFunctionTestTopLevelScope::class,
    HierarchicalMockingTest::class,
    ObjectMockTest::class,
    PartialArgumentMatchingTest::class,
    PrivateMockTest::class,
    RecordingExclusionsTest::class,
    RelaxedMockTest::class,
    ReturningUnitTest::class,
    SpyTest::class,
    VarargsTest::class,
    VerificationConfirmationTest::class,
    VerificationOrderTest::class,
    VerificationTest::class,
    VerificationTimeoutTest::class )
class AllTests {
}
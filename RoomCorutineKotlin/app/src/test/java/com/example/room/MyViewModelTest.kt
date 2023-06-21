package com.example.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.room.entity.Dept
import com.example.room.entity.User
import com.example.room.entity.UserView
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class MyViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var mockRepository: MyRepository

    /** テスト対象 */
    private lateinit var myViewModel: MyViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        // FIXME Dispatchers.Mainだけを使っているテスト(Dispatchersを指定していない)ならこれでもいける
        // Dispatchers.Main以外を使っている場合は、本体のクラスにDispatchersをインジェクションしてやらないといけない
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getUserViewList() = runTest {
        val userViewList = listOf(
            UserView(1, "first1", "last1", "deptName1"),
            UserView(2, "first2", "last2", "deptName2"),
            UserView(3, "first3", "last3", "deptName3"))
        val testFlow = flowOf(userViewList)
        every { mockRepository getProperty "userViewList"  } returns testFlow
        advanceUntilIdle()
        myViewModel = MyViewModel(mockRepository)
        myViewModel.userViewList.observeForever { rsltList ->
            rsltList.forEach { userView ->
                println(userView)
            }
            assertThat(rsltList).isEqualTo(userViewList)
        }
    }

    @Test
    fun insertUser() = runTest {
        coEvery { mockRepository.insertUser(any()) } returns Unit
        every { mockRepository getProperty "userViewList" } returns flowOf(listOf<UserView>())
        myViewModel = MyViewModel(mockRepository)
        myViewModel.insertUser(User(0, "first", "last", "dept"))
        coVerify(exactly = 1) { mockRepository.insertUser(any()) }
    }

    @Test
    fun insertDept() = runTest {
        coEvery { mockRepository.insertDept(any()) } returns Unit
        every { mockRepository getProperty "userViewList" } returns flowOf(listOf<UserView>())
        myViewModel = MyViewModel(mockRepository)
        myViewModel.insertDept(Dept("deptCd", "deptName"))
        coVerify(exactly = 1) { mockRepository.insertDept(any()) }
    }
}
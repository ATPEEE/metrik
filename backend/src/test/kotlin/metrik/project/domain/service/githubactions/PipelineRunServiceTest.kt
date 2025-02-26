package metrik.project.domain.service.githubactions

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import metrik.project.*
import metrik.project.domain.model.Status
import metrik.project.domain.repository.BuildRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class PipelineRunServiceTest {

    @InjectMockKs
    private lateinit var pipelineRunService: PipelineRunService

    @MockK(relaxed = true)
    private lateinit var buildRepository: BuildRepository

    @MockK
    private lateinit var runService: RunService

    @MockK
    private lateinit var branchService: BranchService

    @Test
    fun `should get valid new runs successfully`() {
        every {
            runService.syncRunsByPage(any(), any(), any())
        } returns mutableListOf(
            githubActionsRun1,
            githubActionsRun2
        )
        every {
            branchService.retrieveBranches(any())
        } returns listOf(
            "master",
            "feature/CD pipeline"
        )
        val validNewRuns = pipelineRunService.getNewRuns(githubActionsPipeline)

        Assertions.assertThat(validNewRuns.size).isEqualTo(1)
        Assertions.assertThat(validNewRuns[0]).isEqualTo(githubActionsRun1)
    }

    @Test
    fun `should get in progress runs successfully`() {
        val build = githubActionsExecution.copy(result = Status.IN_PROGRESS, stages = emptyList())
        every { buildRepository.getInProgressBuilds(pipelineId) } returns(listOf(build))
        every { runService.syncSingleRun(any(), any()) } returns(githubActionsRun2)

        val inProgressRuns = pipelineRunService.getInProgressRuns(githubActionsPipeline)

        Assertions.assertThat(inProgressRuns.size).isEqualTo(1)
        Assertions.assertThat(inProgressRuns).isEqualTo(listOf(githubActionsRun2))
    }
}
package metrik.project.domain.service.factory

import metrik.project.domain.model.Execution
import metrik.project.domain.model.PipelineConfiguration
import metrik.project.domain.service.PipelineService
import metrik.project.rest.vo.response.SyncProgress
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service("noopPipelineService")
class NoopPipelineService : PipelineService {
    private var logger = LoggerFactory.getLogger(this.javaClass.name)

    override fun syncBuildsProgressively(pipeline: PipelineConfiguration, emitCb: (SyncProgress) -> Unit)
    : List<Execution> {
        logger.info("Noop implementation")
        return emptyList()
    }

    override fun verifyPipelineConfiguration(pipeline: PipelineConfiguration) {
        logger.info("Noop implementation")
    }

    override fun getStagesSortedByName(pipelineId: String): List<String> {
        logger.info("Noop implementation")
        return emptyList()
    }
}

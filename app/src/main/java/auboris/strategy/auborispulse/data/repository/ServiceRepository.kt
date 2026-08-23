package auboris.strategy.auborispulse.data.repository

import auboris.strategy.auborispulse.data.model.ServiceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalTime

class ServiceRepository {
    private val services: List<ServiceModel> = listOf(
        ServiceModel(
            id = 1,
            name = "Executive Strategy Session",
            description = "A focused working session that converts leadership priorities into a practical 90-day action plan.",
            price = 320.0,
            availableTime = standardSlots,
            imageUrl = "https://images.unsplash.com/photo-1556761175-b413da4baf72?auto=format&fit=crop&w=1200&q=85",
            category = "Strategic Planning",
            durationMinutes = 90,
            features = listOf("Priority mapping", "Decision framework", "90-day roadmap", "Executive summary"),
        ),
        ServiceModel(
            id = 2,
            name = "Growth Strategy Blueprint",
            description = "Market, offer, and capability analysis for leaders preparing the next stage of sustainable growth.",
            price = 780.0,
            availableTime = standardSlots,
            imageUrl = "https://images.unsplash.com/photo-1460925895917-afdab827c52f?auto=format&fit=crop&w=1200&q=85",
            category = "Strategic Planning",
            durationMinutes = 120,
            features = listOf("Market assessment", "Growth scenarios", "Risk review", "Board-ready blueprint"),
        ),
        ServiceModel(
            id = 3,
            name = "Business Process Audit",
            description = "An end-to-end review that identifies bottlenecks, duplicated effort, and high-value automation opportunities.",
            price = 590.0,
            availableTime = standardSlots,
            imageUrl = "https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?auto=format&fit=crop&w=1200&q=85",
            category = "Operational Excellence",
            durationMinutes = 120,
            features = listOf("Process mapping", "Bottleneck analysis", "Efficiency baseline", "Improvement backlog"),
        ),
        ServiceModel(
            id = 4,
            name = "Operating Model Redesign",
            description = "Clarify accountability, workflows, and governance so teams can make better decisions faster.",
            price = 950.0,
            availableTime = standardSlots,
            imageUrl = "https://images.unsplash.com/photo-1552664730-d307ca884978?auto=format&fit=crop&w=1200&q=85",
            category = "Operational Excellence",
            durationMinutes = 150,
            features = listOf("Decision rights", "Governance design", "Role clarity", "Implementation sequence"),
        ),
        ServiceModel(
            id = 5,
            name = "Leadership Team Alignment",
            description = "Build shared expectations, stronger meeting rhythms, and a leadership charter that guides collaboration.",
            price = 640.0,
            availableTime = standardSlots,
            imageUrl = "https://images.unsplash.com/photo-1521737711867-e3b97375f902?auto=format&fit=crop&w=1200&q=85",
            category = "People & Leadership",
            durationMinutes = 120,
            features = listOf("Team diagnostic", "Leadership charter", "Meeting cadence", "Commitment plan"),
        ),
        ServiceModel(
            id = 6,
            name = "Talent & Capability Review",
            description = "Connect business priorities to the roles, skills, and development investments your organisation needs next.",
            price = 520.0,
            availableTime = standardSlots,
            imageUrl = "https://images.unsplash.com/photo-1522202176988-66273c2fd55f?auto=format&fit=crop&w=1200&q=85",
            category = "People & Leadership",
            durationMinutes = 90,
            features = listOf("Capability matrix", "Role gap analysis", "Development priorities", "Talent actions"),
        ),
        ServiceModel(
            id = 7,
            name = "Change Readiness Assessment",
            description = "Measure stakeholder readiness and design the engagement plan for a confident transformation launch.",
            price = 460.0,
            availableTime = standardSlots,
            imageUrl = "https://images.unsplash.com/photo-1551836022-d5d88e9218df?auto=format&fit=crop&w=1200&q=85",
            category = "Transformation",
            durationMinutes = 90,
            features = listOf("Stakeholder map", "Readiness score", "Resistance risks", "Engagement plan"),
        ),
        ServiceModel(
            id = 8,
            name = "Transformation Roadmap",
            description = "Sequence initiatives, ownership, dependencies, and success measures into one executable programme.",
            price = 880.0,
            availableTime = standardSlots,
            imageUrl = "https://images.unsplash.com/photo-1553877522-43269d4ea984?auto=format&fit=crop&w=1200&q=85",
            category = "Transformation",
            durationMinutes = 150,
            features = listOf("Initiative portfolio", "Dependency map", "Milestones", "Benefits tracking"),
        ),
        ServiceModel(
            id = 9,
            name = "Market Opportunity Scan",
            description = "A concise evidence-led assessment of customer shifts, competitors, and attractive market spaces.",
            price = 410.0,
            availableTime = standardSlots,
            imageUrl = "https://images.unsplash.com/photo-1551288049-bebda4e38f71?auto=format&fit=crop&w=1200&q=85",
            category = "Market Intelligence",
            durationMinutes = 75,
            features = listOf("Trend scan", "Competitor view", "Opportunity sizing", "Strategic implications"),
        ),
        ServiceModel(
            id = 10,
            name = "Performance KPI Workshop",
            description = "Create a balanced set of measures that connects daily execution with strategic outcomes.",
            price = 380.0,
            availableTime = standardSlots,
            imageUrl = "https://images.unsplash.com/photo-1543286386-713bdd548da4?auto=format&fit=crop&w=1200&q=85",
            category = "Performance Management",
            durationMinutes = 90,
            features = listOf("Outcome hierarchy", "KPI definitions", "Review rhythm", "Dashboard brief"),
        ),
        ServiceModel(
            id = 11,
            name = "Management System Health Check",
            description = "Evaluate planning, review, escalation, and learning routines across your management system.",
            price = 540.0,
            availableTime = standardSlots,
            imageUrl = "https://images.unsplash.com/photo-1531497865144-0464ef8fb9a9?auto=format&fit=crop&w=1200&q=85",
            category = "Performance Management",
            durationMinutes = 105,
            features = listOf("Routine inventory", "Decision quality review", "Management scorecard", "Recommendations"),
        ),
        ServiceModel(
            id = 12,
            name = "Organisation Structure Review",
            description = "Test whether spans, layers, reporting lines, and interfaces support your strategy and customers.",
            price = 720.0,
            availableTime = standardSlots,
            imageUrl = "https://images.unsplash.com/photo-1497366811353-6870744d04b2?auto=format&fit=crop&w=1200&q=85",
            category = "Organisation Design",
            durationMinutes = 120,
            features = listOf("Structure diagnostic", "Span and layer review", "Interface analysis", "Design options"),
        ),
    )

    companion object {
        private val standardSlots = listOf(
            LocalTime.of(9, 30),
            LocalTime.of(11, 0),
            LocalTime.of(14, 0),
            LocalTime.of(16, 30),
        )
    }

    fun observeAll(): Flow<List<ServiceModel>> {
        return flowOf(services)
    }

    fun observeById(id: Int): Flow<ServiceModel?> {
        val service = services.firstOrNull { service -> service.id == id }
        return flowOf(service)
    }

    fun getById(id: Int): ServiceModel? {
        return services.firstOrNull { service -> service.id == id }
    }
}

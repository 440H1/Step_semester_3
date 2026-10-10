import java.util.*;

enum HackathonState {
    OPEN, JUDGING, PUBLISHED
}

interface TrackScoringRule {
    double calculateScore(double idea, double execution, double presentation);
}

class InnovationTrack implements TrackScoringRule {
    @Override
    public double calculateScore(double idea, double execution, double presentation) {
        return (idea * 0.50) + (execution * 0.30) + (presentation * 0.20);
    }
}

class OpenTrack implements TrackScoringRule {
    @Override
    public double calculateScore(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Project {
    private String title;

    public Project(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

class Score {
    private double idea;
    private double execution;
    private double presentation;

    public Score(double idea, double execution, double presentation) {
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
    }

    public double getIdea() { return idea; }
    public double getExecution() { return execution; }
    public double getPresentation() { return presentation; }

    public void setIdea(double idea) { this.idea = idea; }
    public void setExecution(double execution) { this.execution = execution; }
    public void setPresentation(double presentation) { this.presentation = presentation; }
}

class Team {
    private String teamName;
    private List<Student> members;
    private String trackName;
    private TrackScoringRule scoringRule;
    private Project project;
    private Score score;

    public Team(String teamName, List<Student> members, String trackName, TrackScoringRule scoringRule) {
        this.teamName = teamName;
        this.members = members;
        this.trackName = trackName;
        this.scoringRule = scoringRule;
    }

    public String getTeamName() { return teamName; }
    public List<Student> getMembers() { return members; }
    public String getTrackName() { return trackName; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    public Score getScore() { return score; }
    public void setScore(Score score) { this.score = score; }

    public double getFinalScore() {
        if (score == null) return 0.0;
        return scoringRule.calculateScore(score.getIdea(), score.getExecution(), score.getPresentation());
    }
}

class Hackathon {
    private HackathonState state = HackathonState.OPEN;
    private Map<String, Team> teams = new HashMap<>();
    private Set<String> registeredStudents = new HashSet<>();

    public HackathonState getState() {
        return state;
    }

    public Map<String, Team> getTeams() {
        return teams;
    }

    public void registerTeam(String teamName, List<String> studentNames, String trackName, TrackScoringRule scoringRule) {
        if (studentNames.size() < 2 || studentNames.size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return;
        }

        for (String name : studentNames) {
            if (registeredStudents.contains(name)) {
                System.out.println("Registration failed: Student " + name + " is already in a team.");
                return;
            }
        }

        List<Student> members = new ArrayList<>();
        for (String name : studentNames) {
            registeredStudents.add(name);
            members.add(new Student(name));
        }

        Team team = new Team(teamName, members, trackName, scoringRule);
        teams.put(teamName, team);
        System.out.println("Team " + teamName + " registered (" + studentNames.size() + " members, " + trackName + " track).");
    }

    public void submitProject(String teamName, String projectTitle) {
        Team team = teams.get(teamName);
        if (team != null) {
            team.setProject(new Project(projectTitle));
            System.out.println("Project '" + projectTitle + "' submitted by " + teamName + ".");
        }
    }

    public void publishResults() {
        this.state = HackathonState.PUBLISHED;
        System.out.println("Results published.");
    }
}

class Judge {
    public void scoreProject(Team team, double idea, double execution, double presentation, Hackathon hackathon) {
        if (hackathon.getState() == HackathonState.PUBLISHED) {
            System.out.println("Rescore rejected: Results have already been published.");
            return;
        }
        team.setScore(new Score(idea, execution, presentation));
        System.out.println("Score recorded for '" + team.getProject().getTitle() + "'. Final score: " + String.format(Locale.US, "%.2f", team.getFinalScore()) + ".");
    }
}

public class HackathonManagementSystem {
    public static void main(String[] args) {
        Hackathon hackathon = new Hackathon();
        Judge judge = new Judge();

        hackathon.registerTeam("ByteBusters", Arrays.asList("Asha", "Ravi", "Neha"), "Innovation", new InnovationTrack());
        hackathon.registerTeam("SoloCoder", Arrays.asList("Kiran"), "Open", new OpenTrack());

        hackathon.submitProject("ByteBusters", "SmartAttend");

        Team byteBusters = hackathon.getTeams().get("ByteBusters");
        judge.scoreProject(byteBusters, 8, 7, 9, hackathon);

        hackathon.publishResults();

        judge.scoreProject(byteBusters, 10, 7, 9, hackathon);
    }
}
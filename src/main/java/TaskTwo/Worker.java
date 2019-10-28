package TaskTwo;

public class Worker {
    private int chapters;
    private String job;

    public Worker(String job, int chapters) {
        this.job = job;
        this.chapters = chapters;
    }

    public int getChapters() {
        return chapters;
    }

    public String needMFU() {
        return job;
    }
}

public class UniversalMediaLauncher {

    interface Playable {
        String play();
        String play(int fromSecond);
        String pause();
    }

    static abstract class MediaFile {
        private final String fileId;
        private static int fileCounter = 1000;

        public MediaFile() {
            fileCounter++;
            fileId = "MED-" + fileCounter;
        }

        public String getFileId() {
            return fileId;
        }

        public abstract String getFormatInfo();
    }

    static class AudioFile extends MediaFile implements Playable {
        private String title;

        public AudioFile(String title) {
            this.title = title;
        }

        @Override
        public String getFormatInfo() {
            return "Audio file";
        }

        @Override
        public String play() {
            return "Playing audio: " + title;
        }

        @Override
        public String play(int fromSecond) {
            return "Playing audio: " + title
                    + " from " + fromSecond + "s";
        }

        @Override
        public String pause() {
            return "Audio paused";
        }
    }

    static class Podcast implements Playable {
        private String showName;
        private int episodeNumber;

        public Podcast(String showName, int episodeNumber) {
            this.showName = showName;
            this.episodeNumber = episodeNumber;
        }

        @Override
        public String play() {
            return "Streaming episode " + episodeNumber
                    + " of " + showName;
        }

        @Override
        public String play(int fromSecond) {
            return "Streaming episode " + episodeNumber
                    + " of " + showName
                    + " from " + fromSecond + "s";
        }

        @Override
        public String pause() {
            return "Podcast paused";
        }
    }

    static void launchAll(Playable[] items) {
        for (Playable item : items) {
            System.out.println(item.play());
        }
    }

    public static void main(String[] args) {

        AudioFile a = new AudioFile("Morning Jazz");

        System.out.println(a.play());
        System.out.println(a.play(30));
        System.out.println(a.getFormatInfo()
                + ", ID: " + a.getFileId());

        Podcast p = new Podcast("Tech Talk", 12);

        System.out.println(p.play());

        Playable ref = a;

        System.out.println(ref.play());

        launchAll(new Playable[]{ref, p});
    }
}
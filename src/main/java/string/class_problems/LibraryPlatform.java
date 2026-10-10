import java.util.*;

abstract class Resource implements Comparable<Resource> {
    protected String id;

    public Resource(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Resource other) {
        return this.id.compareTo(other.id);
    }
}

interface Reservable {
    void reserve(String memberId);
}

interface Downloadable {
    void download();
}

class Book extends Resource implements Reservable {
    public Book(String id) {
        super(id);
    }

    @Override
    public void reserve(String memberId) {
        System.out.println(id + " reserved for " + memberId);
    }
}

class EBook extends Resource implements Reservable, Downloadable {
    public EBook(String id) {
        super(id);
    }

    @Override
    public void reserve(String memberId) {
        System.out.println(id + " reserved for " + memberId);
    }

    @Override
    public void download() {
        System.out.println(id + " downloaded");
    }
}

public class LibraryPlatform {
    private List<Resource> resources = new ArrayList<>();

    public void addResource(Resource r) {
        for (Resource existing : resources) {
            if (existing.getId().equals(r.getId())) {
                System.out.println("duplicate rejected");
                return;
            }
        }
        resources.add(r);
        Collections.sort(resources);
        System.out.println(r.getId() + " added");
    }

    public Resource find(String id) {
        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i).getId().equals(id)) {
                System.out.println(id + " found at index " + i);
                return resources.get(i);
            }
        }
        return null;
    }

    public void reserveResource(String id, String memberId) {
        Resource r = getResource(id);
        if (r instanceof Reservable) {
            ((Reservable) r).reserve(memberId);
        } else if (r != null) {
            System.out.println(id + " rejected: reserve unsupported");
        }
    }

    public void downloadResource(String id) {
        Resource r = getResource(id);
        if (r instanceof Downloadable) {
            ((Downloadable) r).download();
        } else if (r != null) {
            System.out.println(id + " rejected: download unsupported");
        }
    }

    private Resource getResource(String id) {
        for (Resource r : resources) {
            if (r.getId().equals(id)) return r;
        }
        return null;
    }

    public static void main(String[] args) {
        LibraryPlatform platform = new LibraryPlatform();

        platform.addResource(new Book("B1"));
        platform.addResource(new Book("B1"));
        platform.addResource(new EBook("E1"));
        platform.reserveResource("B1", "M1");
        platform.downloadResource("B1");
        platform.downloadResource("E1");
        platform.find("E1");
    }
}
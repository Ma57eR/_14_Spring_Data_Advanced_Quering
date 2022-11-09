package _14_SpringAdvancedQueryEx.model.entity;


public interface AuthorNamesWithTotalCopies {
    String getFirstName();
    String getLastName();
    long getTotalCopies();


//    @Override
//    public String toString() {
//        return String.format("%s %s - %d", this.firstName, this.lastName, this.totalCopies);
//    }
}

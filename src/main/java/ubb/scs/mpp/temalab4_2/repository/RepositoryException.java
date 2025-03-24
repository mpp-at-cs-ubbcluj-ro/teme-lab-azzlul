package ubb.scs.mpp.temalab4_2.repository;

public class RepositoryException extends RuntimeException {
    public RepositoryException(){}

    public RepositoryException(String message){
        super(message);
    }
    public RepositoryException(Exception ex){
        super(ex);
    }
}

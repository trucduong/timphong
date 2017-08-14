package service.blog.shared.dto;

public enum CommentType {
    QUESTION, ANSWER;
    
    public static CommentType fromName(String name) {
        for (CommentType e : CommentType.values()) {
            if (e.name().equals(name)) {
                return e;
            }
        }
        return null;
    }
}

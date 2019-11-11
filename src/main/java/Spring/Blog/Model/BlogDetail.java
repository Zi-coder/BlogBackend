package Spring.Blog.Model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"reactor_id" , "blog_id"}))
public class BlogDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Blogs blog;
    @ManyToOne
    private User reactor;
    private byte liked;
    private byte disliked;
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blogs getBlog() {
        return blog;
    }

    public void setBlog(Blogs blog) {
        this.blog = blog;
    }

    public User getReactor() {
        return reactor;
    }

    public void setReactor(User reactor) {
        this.reactor = reactor;
    }

    public byte getLiked() {
        return liked;
    }

    public void setLiked(byte liked) {
        this.liked = liked;
    }

    public byte getDisliked() {
        return disliked;
    }

    public void setDisliked(byte disliked) {
        this.disliked = disliked;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

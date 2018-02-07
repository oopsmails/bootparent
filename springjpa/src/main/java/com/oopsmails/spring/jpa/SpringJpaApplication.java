package com.oopsmails.spring.jpa;

import com.oopsmails.spring.jpa.entity.Comment;
import com.oopsmails.spring.jpa.entity.Post;
import com.oopsmails.spring.jpa.entity.Tag;
import com.oopsmails.spring.jpa.entity.User;
import com.oopsmails.spring.jpa.entity.UserProfile;
import com.oopsmails.spring.jpa.model.Gender;
import com.oopsmails.spring.jpa.repository.CommentRepository;
import com.oopsmails.spring.jpa.repository.PostRepository;
import com.oopsmails.spring.jpa.repository.TagRepository;
import com.oopsmails.spring.jpa.repository.UserProfileRepository;
import com.oopsmails.spring.jpa.repository.UserRepository;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJpaApplication implements CommandLineRunner {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private TagRepository tagRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        prepareToRunOneToOne();
        prepareToRunOneToMany();
        prepareToManyOneToMany();
    }

    private void prepareToRunOneToOne() {
        // Clean up database tables
        userProfileRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();

        //=========================================

        // Create a User instance
        User user = new User("Rajeev", "Singh", "rajeev@callicoder.com",
                "MY_SUPER_SECRET_PASSWORD");

        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1992, 7, 21);

        // Create a UserProfile instance
        UserProfile userProfile = new UserProfile("+91-8197882053", Gender.MALE, dateOfBirth.getTime(),
                "747", "2nd Cross", "Golf View Road, Kodihalli", "Bangalore",
                "Karnataka", "India", "560008");

        // Set child reference(userProfile) in parent entity(user)
        user.setUserProfile(userProfile);

        // Set parent reference(user) in child entity(userProfile)
        userProfile.setUser(user);

        // Save Parent Reference (which will save the child as well)
        userRepository.save(user);

        //=========================================
    }

    private void prepareToRunOneToMany() {
        // Cleanup Database tables
        commentRepository.deleteAllInBatch();
        postRepository.deleteAllInBatch();

        // ======================================

        Post post = new Post("Hibernate One-To-Many Mapping Example",
                "Learn how to use one to many mapping in hibernate",
                "Entire Post Content with sample code");

        Comment comment1 = new Comment("Great Post!");
        comment1.setPost(post);

        Comment comment2 = new Comment("Really helpful Post. Thanks a lot!");
        comment2.setPost(post);

        post.getComments().add(comment1);
        post.getComments().add(comment2);

        postRepository.save(post);

        // ======================================
    }

    private void prepareToManyOneToMany() {
        // Cleanup the tables
//		postRepository.deleteAllInBatch(); // no delete here because need to keep the @OneToMany Post
        tagRepository.deleteAllInBatch();

        // =======================================

        // Create a Post
        Post post = new Post("Hibernate Many to Many Example with Spring Boot",
                "Learn how to map a many to many relationship using hibernate",
                "Entire Post content with Sample code");

        // Create two tags
        Tag tag1 = new Tag("Spring Boot");
        Tag tag2 = new Tag("Hibernate");

        // Add tag references in the post
        post.getTags().add(tag1);
        post.getTags().add(tag2);

        // Add post reference in the tags
        tag1.getPosts().add(post);
        tag2.getPosts().add(post);

        postRepository.save(post);

        // =======================================
    }
}

package pl.kololos.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pl.kololos.api.models.admin.*;
import pl.kololos.api.repositories.PagesRepository;
import pl.kololos.api.repositories.PostsRepository;
import pl.kololos.api.utils.Locals;
import pl.kololos.api.utils.ResourceFileReader;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
@RequiredArgsConstructor
public class AdminService {
    private String shortText;
    private String longText;

    private final PostsRepository postsRepository;
    private final PagesRepository pagesRepository;

    private final ArticleLink articleLink;

    @SuppressWarnings("unused")
    @PostConstruct
    public void onInit() {
        shortText = ResourceFileReader.readFileContent("/shortText.txt");
        longText = ResourceFileReader.readFileContent("/longText.txt");
    }

    public Optional<pl.kololos.api.models.Page> getPageByKind(String pageKind) {
        return pagesRepository.findByKind(pageKind);
    }

    public Posts getPosts(int page) {
        Page<Post> posts = postsRepository.findAll(PageRequest.of(page, PostsPaginationService.PAGE_SIZE));
        List<PostInfo> postInfos = posts.get()
                .map(i -> new PostInfo(i.getId(), i.getTitle(), i.getPublishDateTime().atZone(Locals.ZONE_ID).toLocalDate()))
                .collect(Collectors.toList());
        return new Posts(postInfos);
    }

    public Optional<Post> getPostById(Long postId) {
        return postsRepository.findById(postId);
    }

    public Post savePost(ContentUpdate contentUpdate) {
        Post post = Post.createNew(contentUpdate, articleLink);
        Post savedPost = postsRepository.save(post);
        return savedPost;
    }

    public Post updatePost(Long id, ContentUpdate contentUpdate) {
        Optional<Post> postIfFound = getPostById(id);
        if (postIfFound.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find post");
        }
        Post postToUpdate = postIfFound.get();
        postToUpdate.update(contentUpdate);
        return postsRepository.save(postToUpdate);
    }

    public pl.kololos.api.models.Page updatePage(String pageKind, ContentUpdate contentUpdate) {
        Optional<pl.kololos.api.models.Page> pageByKind = getPageByKind(pageKind);
        if (pageByKind.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find post");
        }
        pl.kololos.api.models.Page pageToUpdate = pageByKind.get();
        pageToUpdate.update(contentUpdate);
        return pagesRepository.save(pageToUpdate);
    }
}

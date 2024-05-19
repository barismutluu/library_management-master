package org.example.librarymanagement.api;

import jakarta.validation.Valid;
import org.example.librarymanagement.business.abstracts.AuthorService;
import org.example.librarymanagement.core.config.modelMapper.ModelMapperService;
import org.example.librarymanagement.core.result.Result;
import org.example.librarymanagement.core.result.ResultData;
import org.example.librarymanagement.core.utils.ResultHelper;
import org.example.librarymanagement.dto.request.author.AuthorSaveRequest;
import org.example.librarymanagement.dto.request.author.AuthorUpdateRequest;
import org.example.librarymanagement.dto.response.author.AuthorResponse;
import org.example.librarymanagement.dto.response.supplier.CursorResponse;
import org.example.librarymanagement.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final ModelMapperService modelMapper;

    public AuthorController(AuthorService authorService, ModelMapperService modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest){
        Author saveAuthor = this.modelMapper.forRequest().map(authorSaveRequest, Author.class);
        this.authorService.save(saveAuthor);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAuthor, AuthorResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> get (@PathVariable("id") long id){
        Author author = this.authorService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(author, AuthorResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AuthorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize) {
        Page<Author> authorPage = this.authorService.cursor(page, pageSize);
        Page<AuthorResponse> authorResponsePage = authorPage
                .map(category -> this.modelMapper.forResponse().map(category, AuthorResponse.class));

        return ResultHelper.cursor(authorResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest){
        this.authorService.get(authorUpdateRequest.getId());
        Author updateAuthor = this.modelMapper.forRequest().map(authorUpdateRequest, Author.class);
        this.authorService.update(updateAuthor);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAuthor, AuthorResponse.class));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id){
        this.authorService.delete(id);
        return ResultHelper.ok();
    }

}

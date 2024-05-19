package org.example.librarymanagement.api;

import jakarta.validation.Valid;
import org.example.librarymanagement.business.abstracts.AuthorService;
import org.example.librarymanagement.business.abstracts.BookService;
import org.example.librarymanagement.core.config.modelMapper.ModelMapperService;
import org.example.librarymanagement.core.result.Result;
import org.example.librarymanagement.core.result.ResultData;
import org.example.librarymanagement.core.utils.ResultHelper;
import org.example.librarymanagement.dto.request.book.BookSaveRequest;
import org.example.librarymanagement.dto.request.book.BookUpdateRequest;
import org.example.librarymanagement.dto.response.book.BookResponse;
import org.example.librarymanagement.dto.response.supplier.CursorResponse;
import org.example.librarymanagement.entities.Author;
import org.example.librarymanagement.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final BookService bookService;
    private final ModelMapperService modelMapper;
    private final AuthorService authorService;

    public BookController(BookService bookService, ModelMapperService modelMapper, AuthorService authorService) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        this.authorService = authorService;
    }

    @GetMapping("{id}")
    public ResultData<BookResponse> getId(@PathVariable("id") long id){
        BookResponse bookResponse = modelMapper.forResponse().map(bookService.get(id), BookResponse.class);
        return ResultHelper.success(bookResponse);
    }
    @PostMapping

    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest){
        BookResponse bookResponse = modelMapper.forResponse().map(bookService.save(modelMapper.forRequest().map(bookSaveRequest, Book.class)), BookResponse.class);
        Author author = this.authorService.get(bookSaveRequest.getAuthorId());
        return ResultHelper.created(bookResponse);
    }
    @PutMapping
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest){
        BookResponse bookResponse = modelMapper.forResponse().map(bookService.update(modelMapper.forRequest().map(bookUpdateRequest, Book.class)), BookResponse.class);
        return ResultHelper.success(bookResponse);
    }
    @DeleteMapping("{id}")
    public Result delete(@PathVariable("id") long id){
        return ResultHelper.success(bookService.delete(id));
    }

    @GetMapping
    public ResultData<CursorResponse<BookResponse>> cursor(@RequestParam(name = "page", required = false, defaultValue = "0") int page, @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        Page<Book> books = bookService.cursor(page,pageSize);
        Page<BookResponse> bookResponses = books.map(category -> modelMapper.forResponse().map(category, BookResponse.class));
        return ResultHelper.cursor(bookResponses);
    }
}

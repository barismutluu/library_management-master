package org.example.librarymanagement.api;

import jakarta.validation.Valid;
import org.example.librarymanagement.business.abstracts.PublisherService;
import org.example.librarymanagement.core.config.modelMapper.ModelMapperService;
import org.example.librarymanagement.core.result.Result;
import org.example.librarymanagement.core.result.ResultData;
import org.example.librarymanagement.core.utils.ResultHelper;
import org.example.librarymanagement.dto.request.publisher.PublisherSaveRequest;
import org.example.librarymanagement.dto.request.publisher.PublisherUpdateRequest;
import org.example.librarymanagement.dto.response.publisher.PublisherResponse;
import org.example.librarymanagement.dto.response.supplier.CursorResponse;
import org.example.librarymanagement.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {
    private final PublisherService publisherService;
    private final ModelMapperService modelMapper;

    public PublisherController(PublisherService publisherService, ModelMapperService modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    public ResultData<PublisherResponse> get(@PathVariable("id") long id){
        PublisherResponse publisherResponse = modelMapper.forResponse().map(publisherService.get(id), PublisherResponse.class);
        return ResultHelper.success(publisherResponse);
    }

    @PostMapping
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest){
        PublisherResponse publisherResponse = modelMapper.forResponse().map(publisherService.save(modelMapper.forRequest().map(publisherSaveRequest, Publisher.class)), PublisherResponse.class);
        return ResultHelper.created(publisherResponse);
    }

    @PutMapping
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest){
        PublisherResponse publisherResponse = modelMapper.forResponse().map(publisherService.update(modelMapper.forRequest().map(publisherUpdateRequest, Publisher.class)), PublisherResponse.class);
        return ResultHelper.created(publisherResponse);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable long id){
        this.publisherService.delete(id);
        return ResultHelper.ok();
    }

    @GetMapping
    public ResultData<CursorResponse<PublisherResponse>> cursor(@RequestParam(name = "page", required = false, defaultValue = "0") int page, @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        Page<Publisher> publishers = publisherService.cursor(page,pageSize);
        Page<PublisherResponse> publisherResponses = publishers.map(category -> modelMapper.forResponse().map(category, PublisherResponse.class));
        return ResultHelper.cursor(publisherResponses);
    }
}

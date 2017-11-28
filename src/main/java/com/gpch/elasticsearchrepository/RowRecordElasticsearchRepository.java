package com.gpch.elasticsearchrepository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.gpch.model.RowRecord;

public interface RowRecordElasticsearchRepository extends ElasticsearchRepository<RowRecord, Long>{
}

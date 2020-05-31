package com.computacion.service;

import org.springframework.stereotype.Service;

import com.computacion.dao.TopicDao;
import com.computacion.model.TsscTopic;
import com.computacion.model.exceptions.TsscTopicException;
import com.computacion.model.exceptions.TsscTopicNotFoundException;
import com.computacion.repository.TsscTopicRepository;

@Service
public class TsscTopicServiceImpl implements TsscTopicService {
	
	private TopicDao topicDao;
	
	public TsscTopicServiceImpl(TopicDao repo) {
		this.topicDao=repo;
	}

	@Override
	public TsscTopic getTopic(long id) throws TsscTopicNotFoundException {
		var result=this.topicDao.findById(id);
		if (result==null) {
			throw new TsscTopicNotFoundException(id);
		}else {
			return result;
		}
	}

	@Override
	public TsscTopic createTopic(TsscTopic topic) throws TsscTopicException {
		
		if(topic != null && topic.getDefaultGroups()>0 && topic.getDefaultSprints()>0) {
			return this.topicDao.save(topic);
		}else {
			throw new TsscTopicException(topic);
		}
		
	}

	@Override
	public TsscTopic updateTopic(TsscTopic topic) throws TsscTopicException, TsscTopicNotFoundException {
		if(topic !=null && this.topicDao.findById(topic.getId())==null) {
			throw new TsscTopicNotFoundException(topic.getId());
		}else {
			if(topic != null && topic.getDefaultGroups()>0 && topic.getDefaultSprints()>0) {
				return this.topicDao.save(topic);
			}else {
				throw new TsscTopicException(topic);
			}
		}
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		this.topicDao.removeAll();;
	}

}

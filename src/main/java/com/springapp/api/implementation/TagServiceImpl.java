package com.springapp.api.implementation;

import com.springapp.api.TagService;
import com.springapp.dao.Factory;
import com.springapp.domain_objects.Tag;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by aurdo on 15/04/14.
 */
@Service
public class TagServiceImpl implements TagService {
    Logger logger = Logger.getLogger(TagService.class.getName());

    public HashSet<Tag> getTagsByName(Collection<String> tags) {
        HashSet<Tag> result = new HashSet<Tag>();
        try {
            result = Factory.getInstance().DAOTag().getByNames(tags);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "DB error", e);
        }
        return result;
    }

    public void addTags(Collection<Tag> tags) {
        try {

            Factory.getInstance().DAOTag().add(tags);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "DB error", e);
        }
    }

    public void addTagsByName(Collection<String> tags) {
        HashSet<Tag> addedTags = new HashSet<Tag>();
        for (String str_tag : tags) {
            Tag tg = new Tag();
            tg.setTag(str_tag);
            addedTags.add(tg);
        }
        this.addTags(addedTags);
    }

    public HashSet<Tag> createTagsForProduct(Collection<String> tags) {
        HashSet<Tag> tag = new HashSet<Tag>();
        tag = this.getTagsByName(tags);
        Set<String> DB_tags = new HashSet<String>();
        for (Tag tg : tag) {
            DB_tags.add(tg.getTag());
        }
        tags.removeAll(DB_tags);

        if (tags.size() != 0) {
            this.addTagsByName(tags);
            tag = this.getTagsByName(tags);
        }

        return tag;
    }
}

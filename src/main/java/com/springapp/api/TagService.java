package com.springapp.api;

import com.springapp.domain_objects.Tag;

import java.util.Collection;
import java.util.List;

public interface TagService {

    //public List<Tag> getTagsByProduct(int id);

    public void removeTag(int id);

    public void addTag(String tag);

    public void addTags(Collection<String> tags);

    public void addTags(String tags[]);

    //public Tag getTag(int id);

    //public void editTag(int id,String tag);
    //public List<Tag>LoadProductByTag(int cat_id,int offset);
}

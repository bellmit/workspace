package com.jumore.jmbi.dao.entity.permission;

import java.io.Serializable;

import com.jumore.dove.aop.Column;
import com.jumore.dove.aop.Entity;
import com.jumore.dove.aop.Id;
import com.jumore.dove.aop.Sequence;
import com.jumore.dove.aop.Table;

@Entity
@Table(name = "perm_menu")
public class Menu implements Serializable {

    /**
     * 
     */
    private static final transient  long serialVersionUID = -1212759066193496564L;

    @Id
    @Sequence
    public Long                         id;

    public String                       name;

    public String                       code;

    public String                       url;

    @Column(name = "father_id")
    public Long                         fatherId;

    public Integer                      sort;

    public Integer                      depth;

    @Column(name = "is_leaf")
    public Integer                      isLeaf;

    public Integer                      status;

    public String                       description;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Menu) {
            Menu another = (Menu) obj;
            return this.id.equals(another.id);
        } else {
            return false;
        }
    }

}

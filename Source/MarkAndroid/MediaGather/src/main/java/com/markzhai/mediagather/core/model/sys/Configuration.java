package com.markzhai.mediagather.core.model.sys;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.markzhai.library.framework.core.model.MZModel;

/**
 * Created by marktlzhai on 2015/3/18.
 */
@Table(name = "sys_conf")
public class Configuration extends MZModel {

    @Column(name = "conf_name", index = true)
    public String confName;

    @Column(name = "conf_value")
    public String confValue;
}

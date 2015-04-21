package com.markzhai.healthkeeper.core.model.medicalcenter;

import com.markzhai.library.framework.core.model.MZModel;
import com.markzhai.library.utils.StringUtils;

/**
 * Created by marktlzhai on 2015/4/21.
 */
public class DepartmentModel extends MZModel {
    public int id;
    public String name;

    public DepartmentModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DepartmentModel(String departmentSTR) {
        if (StringUtils.isEmpty(departmentSTR)) {
            return;
        }
        String[] array = departmentSTR.split(PARSE_SEPARATOR);
        this.id = Integer.valueOf(array[0]);
        this.name = String.valueOf(array[1]);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(64);
        builder.append(id);
        builder.append(SEPARATOR);
        builder.append(name);
        return builder.toString();
    }
}

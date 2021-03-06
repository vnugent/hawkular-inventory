/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hawkular.inventory.api.filters;

import org.hawkular.inventory.api.model.Entity;

import java.util.Arrays;

/**
 * @author Jirka Kremser
 * @since 1.0
 */
public final class RelationWith {
    private RelationWith() {

    }

    public static Ids id(String id) {
        return new Ids(id);
    }

    public static Ids ids(String... ids) {
        return new Ids(ids);
    }

    public static Properties property(String property, String value) {
        return new Properties(property, value);
    }

    @SafeVarargs
    public static Properties properties(String property, String... values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("there must be at least one value of the property");
        }
        Properties properties = new Properties(property, values);
        return properties;
    }


    public static Properties name(String value) {
        return new Properties("label", value);
    }

    @SafeVarargs
    public static Properties names(String... values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("there must be at least one value of the relation name");
        }
        Properties names = new Properties("label", values);
        return names;
    }

    @SafeVarargs
    public static SourceOfType sourcesOfTypes(Class<? extends Entity>... types) {
        return new SourceOfType(types);
    }

    public static SourceOfType sourceOfType(Class<? extends Entity> type) {
        return new SourceOfType(type);
    }

    @SafeVarargs
    public static TargetOfType targetsOfTypes(Class<? extends Entity>... types) {
        return new TargetOfType(types);
    }

    public static TargetOfType targetOfType(Class<? extends Entity> type) {
        return new TargetOfType(type);
    }

    public static final class Ids extends RelationFilter {

        private final String[] ids;

        public Ids(String... ids) {
            this.ids = ids;
        }

        public String[] getIds() {
            return ids;
        }

        @Override
        public String toString() {
            return  "RelationshipIds" + Arrays.asList(ids).toString();
        }
    }

    public static final class Properties extends RelationFilter {

        private final String property;
        private final String[] values;

        public Properties(String property, String... values) {
            this.property = property;
            this.values = values;
        }

        public String getProperty() {
            return property;
        }

        public String[] getValues() {
            return values;
        }

        @Override
        public String toString() {
            return  "RelationshipProperty: " + getProperty() + "=" + Arrays.asList(values).toString();
        }
    }

    public static class SourceOrTargetOfType extends RelationFilter {
        private final Class<? extends Entity>[] types;

        public String getFilterName() {
            return "SourceOrTargetOfType";
        }

        @SafeVarargs
        public SourceOrTargetOfType(Class<? extends Entity>... types) {
            this.types = types;
        }

        public Class<? extends Entity>[] getTypes() {
            return types;
        }

        @Override
        public String toString() {
            StringBuilder ret = new StringBuilder(getFilterName() + "[");
            if (types.length > 0) {
                ret.append(types[0].getSimpleName());

                for(int i = 1; i < types.length; ++i) {
                    ret.append(", ").append(types[i].getSimpleName());
                }
            }
            ret.append("]");
            return ret.toString();
        }
    }

    public static final class SourceOfType extends SourceOrTargetOfType {

        @SafeVarargs
        public SourceOfType(Class<? extends Entity>... types) {
            super(types);
        }

        @Override
        public String getFilterName() {
            return "SourceOfType";
        }
    }

    public static final class TargetOfType extends SourceOrTargetOfType {

        @SafeVarargs
        public TargetOfType(Class<? extends Entity>... types) {
            super(types);
        }

        @Override
        public String getFilterName() {
            return "TargetOfType";
        }
    }

}

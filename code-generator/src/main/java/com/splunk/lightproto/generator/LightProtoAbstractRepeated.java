/**
 * Copyright 2020 Splunk Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.splunk.lightproto.generator;

import io.protostuff.parser.Field;

import java.io.PrintWriter;

import static com.splunk.lightproto.generator.Util.camelCase;

public abstract class LightProtoAbstractRepeated<FieldType extends Field<?>> extends LightProtoField<FieldType> {

    protected final String pluralName;
    protected final String singularName;

    public LightProtoAbstractRepeated(FieldType field, int index) {
        super(field, index);
        this.pluralName = Util.plural(ccName);
        this.singularName = Util.singular(ccName);
    }

    public void has(PrintWriter w) {
    }

    public void fieldClear(PrintWriter w, String enclosingType) {
        w.format("        public %s %s() {\n", enclosingType, Util.camelCase("clear", field.getName()));
        clear(w);
        w.format("            return this;\n");
        w.format("        }\n");
    }
}

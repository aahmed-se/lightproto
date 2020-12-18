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

public class LightProtoBooleanField extends LightProtoNumberField {

    public LightProtoBooleanField(Field<?> field, int index) {
        super(field, index);
    }

    @Override
    public void getter(PrintWriter w) {
        w.format("        public %s %s() {\n", field.getJavaType(), Util.camelCase("is", ccName));
        if (!field.isDefaultValueSet()) {
            w.format("            if (!%s()) {\n", Util.camelCase("has", ccName));
            w.format("                throw new IllegalStateException(\"Field '%s' is not set\");\n", field.getName());
            w.format("            }\n");
        }
        w.format("            return %s;\n", ccName);
        w.format("        }\n");
    }
}

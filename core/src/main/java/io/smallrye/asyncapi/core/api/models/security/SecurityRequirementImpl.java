/*
 * Copyright (C) open knowledge GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package io.smallrye.asyncapi.core.api.models.security;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.smallrye.asyncapi.core.api.models.ModelImpl;
import io.smallrye.asyncapi.spec.models.security.SecurityRequirement;

/**
 * An implementation of the {@link SecurityRequirement} AsyncAPI model interface.
 */
//public class SecurityRequirementImpl extends ExtensibleImpl<SecurityRequirement> implements SecurityRequirement, ModelImpl {
//
//  private String name;
//
//  private List<String> values;
//
//  /**
//   * @see SecurityRequirement#getName()
//   */
//  @Override
//  public String getName() {
//    return null;
//  }
//
//  /**
//   * @see SecurityRequirement#setName(String name)
//   */
//  @Override
//  public void setName(final String name) {
//    this.name = name;
//  }
//
//  /**
//   * @see SecurityRequirement#getValues()
//   */
//  @Override
//  public List<String> getValues() {
//    return ModelUtil.unmodifiableList(this.values);
//  }
//
//  /**
//   * @see SecurityRequirement#setValues(List<String>)
//   */
//  @Override
//  public void setValues(final List<String> values) {
//    this.values = ModelUtil.replace(values, ArrayList::new);
//  }
//
//  /**
//   * @see SecurityRequirement#addValue(String value)
//   */
//  @Override
//  public SecurityRequirement addValue(final String value) {
//    this.values = ModelUtil.add(value, this.values, ArrayList::new);
//    return this;
//  }
//
//  /**
//   * @see SecurityRequirement#removeValue(String value)
//   */
//  @Override
//  public void removeValue(final String value) {
//    ModelUtil.remove(this.values, value);
//  }
//}
//

public class SecurityRequirementImpl extends LinkedHashMap<String, List<String>> implements SecurityRequirement, ModelImpl {

    private static final long serialVersionUID = -2336114397712664136L;

    /**
     * @see SecurityRequirement#addScheme(String securitySchemeName, String scope)
     */
    @Override
    public SecurityRequirement addScheme(String securitySchemeName, String scope) {
        if (scope == null) {
            this.put(securitySchemeName, Collections.emptyList());
        } else {
            this.put(securitySchemeName, Collections.singletonList(scope));
        }
        return this;
    }

    /**
     * @see SecurityRequirement#addScheme(String securitySchemeName, List<String>)
     */
    @Override
    public SecurityRequirement addScheme(String securitySchemeName, List<String> scopes) {
        if (scopes == null) {
            scopes = Collections.emptyList();
        }
        this.put(securitySchemeName, scopes);
        return this;
    }

    /**
     * @see SecurityRequirement#addScheme(String securitySchemeName)
     */
    @Override
    public SecurityRequirement addScheme(String securitySchemeName) {
        this.put(securitySchemeName, Collections.emptyList());
        return this;
    }

    /**
     * @see SecurityRequirement#removeScheme(String securitySchemeName)
     */
    @Override
    public void removeScheme(String securitySchemeName) {
        this.remove(securitySchemeName);
    }

    /**
     * @see SecurityRequirement#getSchemes()
     */
    @Override
    public Map<String, List<String>> getSchemes() {
        return Collections.unmodifiableMap(this);
    }

    /**
     * @see SecurityRequirement#setSchemes(Map)
     */
    @Override
    public void setSchemes(Map<String, List<String>> items) {
        this.clear();
        this.putAll(items);
    }

}

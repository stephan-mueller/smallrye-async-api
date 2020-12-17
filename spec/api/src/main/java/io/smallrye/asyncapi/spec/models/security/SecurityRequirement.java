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
package io.smallrye.asyncapi.spec.models.security;

import java.util.List;
import java.util.Map;

import io.smallrye.asyncapi.spec.models.Constructible;

/**
 * Lists the required security schemes to execute this operation.
 *
 * The name used for each property MUST correspond to a security scheme declared in the Security Schemes under the Components
 * Object.
 *
 * @see "https://www.asyncapi.com/docs/specifications/2.0.0/#securityRequirementObject"
 */
public interface SecurityRequirement extends Constructible {

    /**
     * Adds a security scheme to the SecurityRequirement instance based on the scheme name and required scope (optional)
     * provided.
     *
     * @param securitySchemeName the name of security scheme declared in the Components section of the AsyncAPI document
     * @param scope a required scope - only valid when the defined scheme's type is 'oauth2' or 'openIdConnect'. passing null
     *        will result in an empty list of scopes
     * @return Updated SecurityRequirement instance
     */
    SecurityRequirement addScheme(String securitySchemeName, String scope);

    /**
     * Adds a security scheme to the SecurityRequirement instance based on the scheme name and required scopes (optional)
     * provided.
     *
     * @param securitySchemeName the name of security scheme declared in the Components section of the AsyncAPI document
     * @param scopes the scopes required - only valid when the defined scheme is 'oauth2' or 'openIdConnect'. Passing null will
     *        result in an empty list of scopes
     * @return Updated SecurityRequirement instance
     */
    SecurityRequirement addScheme(String securitySchemeName, List<String> scopes);

    /**
     * Adds a security scheme to the SecurityRequirement instance based on the scheme name.
     *
     * No scopes are included, resulting in an empty list of scopes for the security scheme. This is valid when the defined
     * security scheme is
     * not 'oauth2' or 'openIdConnect'.
     *
     * @param securitySchemeName the name of security scheme declared in the Components section of the AsyncAPI document
     * @return Updated SecurityRequirement instance
     */
    SecurityRequirement addScheme(String securitySchemeName);

    /**
     * Removes a security scheme to the SecurityRequirement instance based on the scheme name.
     *
     * @param securitySchemeName the name of security scheme
     */
    void removeScheme(String securitySchemeName);

    /**
     * Returns a copy map (potentially immutable) of the schemes.
     *
     * @return all items
     */
    Map<String, List<String>> getSchemes();

    /**
     * Set all security schemes to the SecurityRequirement instance.
     *
     * Keys are the name of security scheme declared in the Components section of the AsyncAPI document, values are a list of
     * required scope -
     * only valid when the defined scheme is 'oauth2' or 'openIdConnect'
     *
     * @param items a map containing the security schemes.
     */
    void setSchemes(Map<String, List<String>> items);

    /**
     * Check whether a scheme is present in the map. This is a convenience method for
     * <code>getSchemes().containsKey(name)</code>
     *
     * @param securitySchemeName the name of security scheme
     * @return a boolean to indicate if the scheme is present or not.
     */
    default boolean hasScheme(String securitySchemeName) {
        Map<String, List<String>> map = getSchemes();
        if (map == null) {
            return false;
        }
        return map.containsKey(securitySchemeName);
    }

    /**
     * Returns a list of scopes for a given scheme name. This is a convenience method for <code>getSchemes().get(name)</code>
     *
     * @param securitySchemeName the name of security scheme
     * @return a list of scopes or null.
     */
    default List<String> getScheme(String securitySchemeName) {
        Map<String, List<String>> map = getSchemes();
        if (map == null) {
            return null;
        }
        return map.get(securitySchemeName);
    }
}

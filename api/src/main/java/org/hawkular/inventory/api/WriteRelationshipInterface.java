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
package org.hawkular.inventory.api;

import org.hawkular.inventory.api.model.Entity;
import org.hawkular.inventory.api.model.Relationship;

/**
 * Generic methods to write access to relationships.
 *
 * @param <Single> the access interface to a single relationship
 *
 * @author Jirka Kremser
 * @since 1.0
 */
interface WriteRelationshipInterface<Single> {

    /**
     * Creates a new relationship at the current position in the inventory traversal.
     *
     * <p>Note that there are limitations when working with
     * {@link org.hawkular.inventory.api.Relationships.WellKnown well-known relationships}. See
     * {@link #linkWith(org.hawkular.inventory.api.Relationships.WellKnown, org.hawkular.inventory.api.model.Entity)}
     * for details.
     *
     * @param name the name of the relationship (label)
     * @param targetOrSource the the source/target entity (based on the chosen relationship direction) that the current
     *                       entity (based on the position in the inventory traversal) will be in the relationship with
     * @return access interface to the freshly created relationship
     *
     * @throws java.lang.IllegalArgumentException if any of the parameters is null
     *
     * @see #linkWith(org.hawkular.inventory.api.Relationships.WellKnown, org.hawkular.inventory.api.model.Entity)
     */
    Single linkWith(String name, Entity targetOrSource) throws IllegalArgumentException;

    /**
     * Creates a new relationship at the current position in the inventory traversal.
     *
     * <p>It is possible to have multiple relationships with the same name between 2 entities. These relationships will
     * differ in their ids and can have different properties.
     *
     * <p>Note: please review the comments on the individual well-known relationships (
     * {@link org.hawkular.inventory.api.Relationships.WellKnown#contains contains},
     * {@link org.hawkular.inventory.api.Relationships.WellKnown#defines defines},
     * {@link org.hawkular.inventory.api.Relationships.WellKnown#owns owns}) for restrictions of usage, especially what
     * restrictions the relationships impose when deleting entities.
     *
     * @param name the well known name (Relationships.WellKnown) of the relationship
     * @param targetOrSource the the source/target entity (based on the chosen relationship direction) that the current
     *                       entity (based on the position in the inventory traversal) will be in the relationship with
     * @return access interface to the freshly created relationship
     *
     * @throws java.lang.IllegalArgumentException if any of the parameters is null
     */
    Single linkWith(Relationships.WellKnown name, Entity targetOrSource) throws IllegalArgumentException;

    /**
     * Persists the provided relationship on the current position in the inventory traversal.
     *
     * @param relationship the relationship to update
     *
     * @throws org.hawkular.inventory.api.RelationNotFoundException if the relationship is not found in the database
     * @throws java.lang.IllegalArgumentException if the source/target entity (based on the chosen relationship
     * direction) doesn't correspond with the current position in the inventory traversal or if the name of the
     * relationship doesn't correspond to what's in the database
     */
    void update(Relationship relationship) throws RelationNotFoundException;

    /**
     * Deletes an relationship with the provided id from the current position in the inventory traversal.
     *
     * @param id the id of the relationship to delete
     * @throws org.hawkular.inventory.api.RelationNotFoundException
     */
    void delete(String id) throws RelationNotFoundException;
}

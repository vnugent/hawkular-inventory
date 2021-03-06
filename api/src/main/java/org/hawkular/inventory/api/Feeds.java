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

import org.hawkular.inventory.api.model.Feed;

/**
 * @author Lukas Krejci
 * @since 1.0
 */
public final class Feeds {

    private Feeds() {

    }

    private interface BrowserBase {
        Resources.Read resources();
    }

    public interface Single extends SingleRelatableEntityBrowser<Feed>, BrowserBase {}

    public interface Multiple extends MultipleRelatableEntityBrowser<Feed>, BrowserBase {}

    public interface Read extends ReadInterface<Single, Multiple> {}

    public interface ReadAndRegister extends ReadInterface<Single, Multiple> {
        Single register(String proposedId);
    }

    public interface ReadRelate extends ReadInterface<Single, Multiple>, RelateInterface {}
}

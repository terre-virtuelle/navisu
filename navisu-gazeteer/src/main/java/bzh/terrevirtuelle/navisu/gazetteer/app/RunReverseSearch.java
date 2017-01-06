/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package bzh.terrevirtuelle.navisu.gazetteer.app;

import bzh.terrevirtuelle.navisu.gazetteer.impl.lucene.GeoNameResolver;
import bzh.terrevirtuelle.navisu.gazetteer.impl.lucene.domain.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RunReverseSearch {

    
    
    /**
     * One sample program to run reverse search
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String indexPath;
        indexPath = "/home/serge/Data/allCountries/geoIndex";
        try (GeoNameResolver resolver = new GeoNameResolver()) {
           resolver.buildIndex("/home/serge/Data/allCountries/allCountries.txt", indexPath, true);
            System.out.println("Places Near Brest");
            System.out.println(resolver.searchNearby(-4.48483, 48.39146, 5.0, indexPath, 10));
            List<String> locationNames = new ArrayList<>();
            locationNames.add("Brest");
            Map<String, List<Location>> locations = resolver.searchGeoName(indexPath, locationNames, 10);
            System.out.println("locations " + locations);
            System.out.println(locations.keySet());
            System.out.println(locations.values());
        }

    }

}

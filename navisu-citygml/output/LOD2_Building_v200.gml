<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<CityModel xmlns:xAL="urn:oasis:names:tc:ciq:xsdschema:xAL:2.0" xmlns:gml="http://www.opengis.net/gml" xmlns:wtr="http://www.opengis.net/citygml/waterbody/2.0" xmlns:app="http://www.opengis.net/citygml/appearance/2.0" xmlns:tex="http://www.opengis.net/citygml/texturedsurface/2.0" xmlns="http://www.opengis.net/citygml/2.0" xmlns:veg="http://www.opengis.net/citygml/vegetation/2.0" xmlns:dem="http://www.opengis.net/citygml/relief/2.0" xmlns:tran="http://www.opengis.net/citygml/transportation/2.0" xmlns:bldg="http://www.opengis.net/citygml/building/2.0" xmlns:grp="http://www.opengis.net/citygml/cityobjectgroup/2.0" xmlns:tun="http://www.opengis.net/citygml/tunnel/2.0" xmlns:frn="http://www.opengis.net/citygml/cityfurniture/2.0" xmlns:brid="http://www.opengis.net/citygml/bridge/2.0" xmlns:gen="http://www.opengis.net/citygml/generics/2.0" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:luse="http://www.opengis.net/citygml/landuse/2.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.opengis.net/citygml/waterbody/2.0 http://schemas.opengis.net/citygml/waterbody/2.0/waterBody.xsd http://www.opengis.net/citygml/appearance/2.0 http://schemas.opengis.net/citygml/appearance/2.0/appearance.xsd http://www.opengis.net/citygml/texturedsurface/2.0 http://schemas.opengis.net/citygml/texturedsurface/2.0/texturedSurface.xsd http://www.opengis.net/citygml/vegetation/2.0 http://schemas.opengis.net/citygml/vegetation/2.0/vegetation.xsd http://www.opengis.net/citygml/relief/2.0 http://schemas.opengis.net/citygml/relief/2.0/relief.xsd http://www.opengis.net/citygml/transportation/2.0 http://schemas.opengis.net/citygml/transportation/2.0/transportation.xsd http://www.opengis.net/citygml/building/2.0 http://schemas.opengis.net/citygml/building/2.0/building.xsd http://www.opengis.net/citygml/cityobjectgroup/2.0 http://schemas.opengis.net/citygml/cityobjectgroup/2.0/cityObjectGroup.xsd http://www.opengis.net/citygml/tunnel/2.0 http://schemas.opengis.net/citygml/tunnel/2.0/tunnel.xsd http://www.opengis.net/citygml/cityfurniture/2.0 http://schemas.opengis.net/citygml/cityfurniture/2.0/cityFurniture.xsd http://www.opengis.net/citygml/bridge/2.0 http://schemas.opengis.net/citygml/bridge/2.0/bridge.xsd http://www.opengis.net/citygml/generics/2.0 http://schemas.opengis.net/citygml/generics/2.0/generics.xsd http://www.opengis.net/citygml/landuse/2.0 http://schemas.opengis.net/citygml/landuse/2.0/landUse.xsd">
  <gml:boundedBy>
    <gml:Envelope srsDimension="3">
      <gml:lowerCorner>0.0 0.0 0.0</gml:lowerCorner>
      <gml:upperCorner>6.0 12.0 9.0</gml:upperCorner>
    </gml:Envelope>
  </gml:boundedBy>
  <cityObjectMember>
    <bldg:Building gml:id="ID_test-building">
      <creationDate>2019-10-20</creationDate>
      <gen:intAttribute name="noOfInhabitants">
        <gen:value>5</gen:value>
      </gen:intAttribute>
      <bldg:class>residential building</bldg:class>
      <bldg:lod2Solid>
        <gml:Solid>
          <gml:exterior>
            <gml:CompositeSurface>
              <gml:surfaceMember xlink:href="#UUID_ca128712-1f2d-4dd9-9f6f-7763f28f99f3"/>
              <gml:surfaceMember xlink:href="#UUID_86bdded5-5bcd-45bf-b735-6ba5839c9b77"/>
              <gml:surfaceMember xlink:href="#UUID_7bd5620d-7e81-4d7a-a662-823304715a35"/>
              <gml:surfaceMember xlink:href="#UUID_490f0177-8342-45c0-8ddd-5a095c270fdd"/>
              <gml:surfaceMember xlink:href="#UUID_24434942-005d-46cb-81e4-cf6de6530bb2"/>
              <gml:surfaceMember xlink:href="#UUID_da91b2d2-5a55-49c2-a603-ebdc591cff81"/>
              <gml:surfaceMember xlink:href="#UUID_6b51fa93-2fc7-4f79-a0f3-e2a2e2a9714c"/>
            </gml:CompositeSurface>
          </gml:exterior>
        </gml:Solid>
      </bldg:lod2Solid>
      <bldg:boundedBy>
        <bldg:GroundSurface gml:id="UUID_1ba03ca4-32a4-441a-b595-f5ed904df618">
          <bldg:lod2MultiSurface>
            <gml:MultiSurface>
              <gml:surfaceMember>
                <gml:Polygon gml:id="UUID_ca128712-1f2d-4dd9-9f6f-7763f28f99f3">
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList srsDimension="3">0.0 0.0 0.0 0.0 12.0 0.0 6.0 12.0 0.0 6.0 0.0 0.0 0.0 0.0 0.0</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
            </gml:MultiSurface>
          </bldg:lod2MultiSurface>
        </bldg:GroundSurface>
      </bldg:boundedBy>
      <bldg:boundedBy>
        <bldg:WallSurface gml:id="UUID_dff919e4-f361-4c99-886b-4e13de965367">
          <bldg:lod2MultiSurface>
            <gml:MultiSurface>
              <gml:surfaceMember>
                <gml:Polygon gml:id="UUID_86bdded5-5bcd-45bf-b735-6ba5839c9b77">
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList srsDimension="3">6.0 0.0 0.0 6.0 12.0 0.0 6.0 12.0 6.0 6.0 0.0 6.0 6.0 0.0 0.0</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
            </gml:MultiSurface>
          </bldg:lod2MultiSurface>
        </bldg:WallSurface>
      </bldg:boundedBy>
      <bldg:boundedBy>
        <bldg:WallSurface gml:id="UUID_7c29b2fe-1347-4ef8-96be-c2ddca52489b">
          <bldg:lod2MultiSurface>
            <gml:MultiSurface>
              <gml:surfaceMember>
                <gml:Polygon gml:id="UUID_7bd5620d-7e81-4d7a-a662-823304715a35">
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList srsDimension="3">0.0 0.0 0.0 0.0 0.0 6.0 0.0 12.0 6.0 0.0 12.0 0.0 0.0 0.0 0.0</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
            </gml:MultiSurface>
          </bldg:lod2MultiSurface>
        </bldg:WallSurface>
      </bldg:boundedBy>
      <bldg:boundedBy>
        <bldg:WallSurface gml:id="UUID_41f58169-c7db-4db9-b48d-8fb0cb3931d9">
          <bldg:lod2MultiSurface>
            <gml:MultiSurface>
              <gml:surfaceMember>
                <gml:Polygon gml:id="UUID_490f0177-8342-45c0-8ddd-5a095c270fdd">
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList srsDimension="3">0.0 0.0 0.0 6.0 0.0 0.0 6.0 0.0 6.0 3.0 0.0 9.0 0.0 0.0 6.0 0.0 0.0 0.0</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
            </gml:MultiSurface>
          </bldg:lod2MultiSurface>
        </bldg:WallSurface>
      </bldg:boundedBy>
      <bldg:boundedBy>
        <bldg:WallSurface gml:id="UUID_a28209fe-7a7b-4e63-888d-33f7eeb34ba3">
          <bldg:lod2MultiSurface>
            <gml:MultiSurface>
              <gml:surfaceMember>
                <gml:Polygon gml:id="UUID_24434942-005d-46cb-81e4-cf6de6530bb2">
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList srsDimension="3">6.0 12.0 0.0 0.0 12.0 0.0 0.0 12.0 6.0 3.0 12.0 9.0 6.0 12.0 6.0 6.0 12.0 0.0</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
            </gml:MultiSurface>
          </bldg:lod2MultiSurface>
        </bldg:WallSurface>
      </bldg:boundedBy>
      <bldg:boundedBy>
        <bldg:RoofSurface gml:id="UUID_c9c5aff1-3b0d-447f-b766-90cc29a12f39">
          <bldg:lod2MultiSurface>
            <gml:MultiSurface>
              <gml:surfaceMember>
                <gml:Polygon gml:id="UUID_da91b2d2-5a55-49c2-a603-ebdc591cff81">
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList srsDimension="3">6.0 0.0 6.0 6.0 12.0 6.0 3.0 12.0 9.0 3.0 0.0 9.0 6.0 0.0 6.0</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
            </gml:MultiSurface>
          </bldg:lod2MultiSurface>
        </bldg:RoofSurface>
      </bldg:boundedBy>
      <bldg:boundedBy>
        <bldg:RoofSurface gml:id="UUID_1ecbc3ee-8c33-4868-86a9-77a137c1bd0a">
          <bldg:lod2MultiSurface>
            <gml:MultiSurface>
              <gml:surfaceMember>
                <gml:Polygon gml:id="UUID_6b51fa93-2fc7-4f79-a0f3-e2a2e2a9714c">
                  <gml:exterior>
                    <gml:LinearRing>
                      <gml:posList srsDimension="3">0.0 0.0 6.0 3.0 0.0 9.0 3.0 12.0 9.0 0.0 12.0 6.0 0.0 0.0 6.0</gml:posList>
                    </gml:LinearRing>
                  </gml:exterior>
                </gml:Polygon>
              </gml:surfaceMember>
            </gml:MultiSurface>
          </bldg:lod2MultiSurface>
        </bldg:RoofSurface>
      </bldg:boundedBy>
    </bldg:Building>
  </cityObjectMember>
</CityModel>
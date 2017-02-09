package bzh.terrevirtuelle.navisu.app.guiagent.svg;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;

public class SVGContentBuilder {

    private URL url;
    private SVGContent root;
    private Map<String, Paint> gradients;

    public SVGContentBuilder(URL url) {
        this.url = url;
        this.root = new SVGContent();

        gradients = new HashMap<>();
    }

    protected SVGContent build() throws IOException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty("javax.xml.stream.isValidating", false);
        factory.setProperty("javax.xml.stream.isNamespaceAware", false);
        factory.setProperty("javax.xml.stream.supportDTD", false);

        try (BufferedInputStream bufferedStream = new BufferedInputStream(url.openStream())) {
            XMLEventReader reader = factory.createXMLEventReader(bufferedStream);

            eventLoop(reader, root);
            reader.close();
        }

        return root;
    }

    private void eventLoop(XMLEventReader reader, Group group) throws IOException, XMLStreamException {
        if (group == null) {
            group = new Group();
        }

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isStartElement()) {
                StartElement element = (StartElement) event;

                Node node = null;
                switch (element.getName().toString()) {
                    case "rect":
                        node = buildRect(element);
                        break;
                    case "circle":
                        node = buildCircle(element);
                        break;
                    case "ellipse":
                        node = buildEllipse(element);
                        break;
                    case "path":
                        node = buildPath(element);
                        break;
                    case "polygon":
                        node = buildPolygon(element);
                        break;
                    case "line":
                        node = buildLine(element);
                        break;
                    case "polyline":
                        node = buildPolyline(element);
                        break;
                    case "text":
                        node = buildText(reader, element);
                        break;
                    case "image":
                        node = buildImage(reader, element);
                        break;
                    case "svg":
                    case "g":
                        node = buildGroup(reader, element);
                        break;
                    case "linearGradient":
                        buildLinearGradient(reader, element);
                        break;
                    case "radialGradient":
                        buildRadialGradient(reader, element);
                        break;
                    default:
                        Logger.getLogger(SVGContentBuilder.class.getName()).log(Level.INFO, "Non Support Element: {0}", element);
                        break;
                }
                if (node != null) {
                    if (node instanceof Shape) {
                        setShapeStyle((Shape) node, element);
                    }

                    setOpacity(node, element);
                    setTransform(node, element);

                    Attribute idAttribute = element.getAttributeByName(new QName("id"));
                    if (idAttribute != null) {
                        root.putNode(idAttribute.getValue(), node);
                    }
                    group.getChildren().add(node);
                }
            } else if (event.isEndElement()) {
                EndElement element = (EndElement) event;
                if (element.getName().toString().equals("g")) {
                    return;
                }
            }
        }
    }

    private Group buildGroup(XMLEventReader reader, StartElement element) throws IOException, XMLStreamException {
        Group group = new Group();
        eventLoop(reader, group);

        return group;
    }

    private void buildRadialGradient(XMLEventReader reader, StartElement element) throws IOException, XMLStreamException {
        String id = null;
        Double fx = null;
        Double fy = null;
        Double cx = null;
        Double cy = null;
        Double r = null;
        Transform transform = null;

        @SuppressWarnings("unchecked")
        Iterator<Attribute> it = element.getAttributes();
        while (it.hasNext()) {
            Attribute attribute = it.next();
            switch (attribute.getName().getLocalPart()) {
                case "id":
                    id = attribute.getValue();
                    break;
                case "gradientUnits":
                    String gradientUnits = attribute.getValue();
                    if (!gradientUnits.equals("userSpaceOnUse")) {
                        Logger.getLogger(SVGContentBuilder.class.getName()).log(Level.INFO, "LinearGradient supports only userSpaceOnUse: {0}", element);
                        return;
                    }
                    break;
                case "fx":
                    fx = Double.valueOf(attribute.getValue());
                    break;
                case "fy":
                    fy = Double.valueOf(attribute.getValue());
                    break;
                case "cx":
                    cx = Double.valueOf(attribute.getValue());
                    break;
                case "cy":
                    cy = Double.valueOf(attribute.getValue());
                    break;
                case "r":
                    r = Double.valueOf(attribute.getValue());
                    break;
                case "gradientTransform":
                    transform = extractTransform(attribute.getValue());
                    break;
                default:
                    Logger.getLogger(SVGContentBuilder.class.getName()).log(Level.INFO, "RadialGradient doesn''t supports: {0}", element);
                    break;
            }
        }

        // Stop の読み込み
        List<Stop> stops = buildStops(reader, "radialGradient");
        
        if (id != null && cx != null && cy != null && r != null) {
            double fDistance = 0.0;
            double fAngle = 0.0;

            if (transform != null && transform instanceof Affine) {
                double tempCx = cx;
                double tempCy = cy;
                double tempR = r;

                Affine affine = (Affine) transform;
                cx = tempCx * affine.getMxx() + tempCy * affine.getMxy() + affine.getTx();
                cy = tempCx * affine.getMyx() + tempCy * affine.getMyy() + affine.getTy();
                
                // これは多分違う
                r = Math.sqrt(tempR * affine.getMxx() * tempR * affine.getMxx()
                                + tempR * affine.getMyx() * tempR * affine.getMyx());
                
                if (fx != null && fy != null) {
                    double tempFx = fx;
                    double tempFy = fy;
                    fx = tempFx * affine.getMxx() + tempFy * affine.getMxy() + affine.getTx();
                    fy = tempFx * affine.getMyx() + tempFy * affine.getMyy() + affine.getTy();
                } else {
                    fAngle = Math.asin(affine.getMyx()) * 180.0 / Math.PI;
                    // これもかなり怪しい
                    fDistance = Math.sqrt((cx - tempCx) * (cx - tempCx) + (cy - tempCy) * (cy - tempCy));
                }
            }

            if (fx != null && fy != null) {
                fDistance = Math.sqrt((fx - cx) * (fx - cx) + (fy - cy) * (fy - cy)) / r;
                fAngle = Math.atan2(cy - fy, cx - fx) * 180.0 / Math.PI;
            }

            RadialGradient gradient = new RadialGradient(fAngle, fDistance, cx, cy, r, false, CycleMethod.NO_CYCLE, stops);
            gradients.put(id, gradient);
        }
    }

    private void buildLinearGradient(XMLEventReader reader, StartElement element) throws IOException, XMLStreamException {
        String id = null;
        double x1 = Double.NaN;
        double y1 = Double.NaN;
        double x2 = Double.NaN;
        double y2 = Double.NaN;
        Transform transform = null;

        @SuppressWarnings("unchecked")
        Iterator<Attribute> it = element.getAttributes();
        while (it.hasNext()) {
            Attribute attribute = it.next();
            switch (attribute.getName().getLocalPart()) {
                case "id":
                    id = attribute.getValue();
                    break;
                case "gradientUnits":
                    String gradientUnits = attribute.getValue();
                    if (!gradientUnits.equals("userSpaceOnUse")) {
                        Logger.getLogger(SVGContentBuilder.class.getName()).log(Level.INFO, "LinearGradient supports only userSpaceOnUse: {0}", element);
                        return;
                    }
                    break;
                case "x1":
                    x1 = Double.parseDouble(attribute.getValue());
                    break;
                case "y1":
                    y1 = Double.parseDouble(attribute.getValue());
                    break;
                case "x2":
                    x2 = Double.parseDouble(attribute.getValue());
                    break;
                case "y2":
                    y2 = Double.parseDouble(attribute.getValue());
                    break;
                case "gradientTransform":
                    transform = extractTransform(attribute.getValue());
                    break;
                default:
                    Logger.getLogger(SVGContentBuilder.class.getName()).log(Level.INFO, "LinearGradient doesn''t supports: {0}:{1}", new Object[]{attribute, element});
                    break;
            }
        }

        // Stop の読み込み
        List<Stop> stops = buildStops(reader, "linearGradient");

        if (id != null && x1 != Double.NaN && y1 != Double.NaN && x2 != Double.NaN && y2 != Double.NaN) {
            if (transform != null && transform instanceof Affine) {
                double x1d = x1;
                double y1d = y1;
                double x2d = x2;
                double y2d = y2;
                Affine affine = (Affine) transform;
                x1 = x1d * affine.getMxx() + y1d * affine.getMxy() + affine.getTx();
                y1 = x1d * affine.getMyx() + y1d * affine.getMyy() + affine.getTy();
                x2 = x2d * affine.getMxx() + y2d * affine.getMxy() + affine.getTx();
                y2 = x2d * affine.getMyx() + y2d * affine.getMyy() + affine.getTy();
            }

            LinearGradient gradient = new LinearGradient(x1, y1, x2, y2, false, CycleMethod.NO_CYCLE, stops);
            gradients.put(id, gradient);
        }
    }

    private List<Stop> buildStops(XMLEventReader reader, String kindOfGradient) throws XMLStreamException {
        List<Stop> stops = new ArrayList<>();

        while (true) {
            XMLEvent event = reader.nextEvent();
            if (event.isEndElement() && event.asEndElement().getName().getLocalPart().equals(kindOfGradient)) {
                break;
            } else if (event.isStartElement()) {
                StartElement element = event.asStartElement();
                if (!element.getName().getLocalPart().equals("stop")) {
                    Logger.getLogger(SVGContentBuilder.class.getName()).log(Level.INFO, "LinearGradient doesn''t supports: {0}", element);
                    continue;
                }

                double offset = Double.NaN;
                String color = null;
                double opacity = 1.0;

                @SuppressWarnings("unchecked")
                Iterator<Attribute> it = element.getAttributes();
                while (it.hasNext()) {

                    Attribute attribute = it.next();
                    switch (attribute.getName().getLocalPart()) {
                        case "offset":
                            offset = Double.parseDouble(attribute.getValue());
                            break;
                        case "style":
                            String style = attribute.getValue();
                            StringTokenizer tokenizer = new StringTokenizer(style, ";");
                            while (tokenizer.hasMoreTokens()) {
                                String item = tokenizer.nextToken().trim();
                                if (item.startsWith("stop-color")) {
                                    color = item.substring(11);
                                } else if (item.startsWith("stop-opacity")) {
                                    opacity = Double.parseDouble(item.substring(13));
                                } else {
                                    Logger.getLogger(SVGContentBuilder.class.getName()).log(Level.INFO, "LinearGradient Stop doesn''t supports: {0} [{1}] ''{2}''", new Object[]{attribute, element, item});
                                }
                            }
                            break;
                        default:
                            Logger.getLogger(SVGContentBuilder.class.getName()).log(Level.INFO, "LinearGradient Stop doesn''t supports: {0} [{1}]", new Object[]{attribute, element});
                            break;
                    }
                }

                if (offset != Double.NaN && color != null) {
                    Color colour = Color.web(color, opacity);
                    Stop stop = new Stop(offset, colour);
                    stops.add(stop);
                }
            }
        }

        return stops;
    }

    private Shape buildRect(StartElement element) {
        Attribute xAttribute = element.getAttributeByName(new QName("x"));
        Attribute yAttribute = element.getAttributeByName(new QName("y"));
        Attribute widthAttribute = element.getAttributeByName(new QName("width"));
        Attribute heightAttribute = element.getAttributeByName(new QName("height"));

        double x = 0.0;
        double y = 0.0;
        
        if (xAttribute != null) {
            x = Double.parseDouble(xAttribute.getValue());
        }
        if (yAttribute != null) {
            y = Double.parseDouble(yAttribute.getValue());
        }
        Rectangle rect = new Rectangle(x, y,
                Double.parseDouble(widthAttribute.getValue()),
                Double.parseDouble(heightAttribute.getValue()));

        return rect;
    }

    private Shape buildCircle(StartElement element) {
        Attribute cxAttribute = element.getAttributeByName(new QName("cx"));
        Attribute cyAttribute = element.getAttributeByName(new QName("cy"));
        Attribute radiusAttribute = element.getAttributeByName(new QName("r"));

        Circle circle = new Circle(Double.parseDouble(cxAttribute.getValue()),
                Double.parseDouble(cyAttribute.getValue()),
                Double.parseDouble(radiusAttribute.getValue()));

        return circle;
    }

    private Shape buildEllipse(StartElement element) {
        Attribute cxAttribute = element.getAttributeByName(new QName("cx"));
        Attribute cyAttribute = element.getAttributeByName(new QName("cy"));
        Attribute radiusXAttribute = element.getAttributeByName(new QName("rx"));
        Attribute radiusYAttribute = element.getAttributeByName(new QName("ry"));

        Ellipse ellipse = new Ellipse(Double.parseDouble(cxAttribute.getValue()),
                Double.parseDouble(cyAttribute.getValue()),
                Double.parseDouble(radiusXAttribute.getValue()),
                Double.parseDouble(radiusYAttribute.getValue()));

        return ellipse;
    }

    private Shape buildPath(StartElement element) {
        Attribute dAttribute = element.getAttributeByName(new QName("d"));

        SVGPath path = new SVGPath();
        path.setContent(dAttribute.getValue());

        return path;
    }

    private Shape buildPolygon(StartElement element) {
        Attribute pointsAttribute = element.getAttributeByName(new QName("points"));
        Polygon polygon = new Polygon();

        StringTokenizer tokenizer = new StringTokenizer(pointsAttribute.getValue(), " ");
        while (tokenizer.hasMoreTokens()) {
            String point = tokenizer.nextToken();

            StringTokenizer tokenizer2 = new StringTokenizer(point, ",");
            Double x = Double.valueOf(tokenizer2.nextToken());
            Double y = Double.valueOf(tokenizer2.nextToken());

            polygon.getPoints().add(x);
            polygon.getPoints().add(y);
        }

        return polygon;
    }

    private Shape buildLine(StartElement element) {
        Attribute x1Attribute = element.getAttributeByName(new QName("x1"));
        Attribute y1Attribute = element.getAttributeByName(new QName("y1"));
        Attribute x2Attribute = element.getAttributeByName(new QName("x2"));
        Attribute y2Attribute = element.getAttributeByName(new QName("y2"));

        if (x1Attribute != null && y1Attribute != null && x2Attribute != null && y2Attribute != null) {
            double x1 = Double.parseDouble(x1Attribute.getValue());
            double y1 = Double.parseDouble(y1Attribute.getValue());
            double x2 = Double.parseDouble(x2Attribute.getValue());
            double y2 = Double.parseDouble(y2Attribute.getValue());

            return new Line(x1, y1, x2, y2);
        } else {
            return null;
        }
    }

    private Shape buildPolyline(StartElement element) {
        Polyline polyline = new Polyline();
        Attribute pointsAttribute = element.getAttributeByName(new QName("points"));
        
        StringTokenizer tokenizer = new StringTokenizer(pointsAttribute.getValue(), " ");
        while (tokenizer.hasMoreTokens()) {
            String points = tokenizer.nextToken();
            StringTokenizer tokenizer2 = new StringTokenizer(points, ",");
            double x = Double.parseDouble(tokenizer2.nextToken());
            double y = Double.parseDouble(tokenizer2.nextToken());
            polyline.getPoints().add(x);
            polyline.getPoints().add(y);
        }

        return polyline;
    }

    private Shape buildText(XMLEventReader reader, StartElement element) throws XMLStreamException {
        Attribute fontFamilyAttribute = element.getAttributeByName(new QName("font-family"));
        Attribute fontSizeAttribute = element.getAttributeByName(new QName("font-size"));

        // TODO styleにfontの指定がある場合
        Font font = null;
        if (fontFamilyAttribute != null && fontSizeAttribute != null) {
            font = Font.font(fontFamilyAttribute.getValue().replace("'", ""),
                                  Double.parseDouble(fontSizeAttribute.getValue()));
        }
        
        XMLEvent event = reader.nextEvent();
        if (event.isCharacters()) {
            Text text = new Text(((Characters) event).getData());
            if (font != null) {
                text.setFont(font);
            }

            return text;
        } else {
            throw new XMLStreamException("Illegal Element: " + event);
        }
    }

    private ImageView buildImage(XMLEventReader reader, StartElement element) throws IOException {
        Attribute widthAttribute = element.getAttributeByName(new QName("width"));
        double width = Double.parseDouble(widthAttribute.getValue());
        Attribute heightAttribute = element.getAttributeByName(new QName("height"));
        double height = Double.parseDouble(heightAttribute.getValue());
        Attribute hrefAttribute = element.getAttributeByName(new QName("href"));

        URL imageUrl = null;
        try {
            imageUrl = new URL(hrefAttribute.getValue());
        } catch (MalformedURLException ex) {
            try {
                imageUrl = new URL(url, hrefAttribute.getValue());
            } catch (MalformedURLException ex1) {
                Logger.getLogger(SVGContentBuilder.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        Image image = new Image(imageUrl.toString(), width, height, true, true);

        return new ImageView(image);
    }

    private void setTransform(Node node, StartElement element) {
        Attribute transformAttribute = element.getAttributeByName(new QName("transform"));
        if (transformAttribute != null) {
            String transforms = transformAttribute.getValue();

            Transform transform = extractTransform(transforms);
            node.getTransforms().add(transform);
        }
    }

    private Transform extractTransform(String transforms) {
        Transform transform = null;

        StringTokenizer tokenizer = new StringTokenizer(transforms, ")");

        while (tokenizer.hasMoreTokens()) {
            String transformTxt = tokenizer.nextToken();
            if (transformTxt.startsWith("translate(")) {
                throw new UnsupportedOperationException("Transform:Translate");
            } else if (transformTxt.startsWith("scale(")) {
                throw new UnsupportedOperationException("Transform:Scale");
            } else if (transformTxt.startsWith("rotate(")) {
                throw new UnsupportedOperationException("Transform:Rotate");
            } else if (transformTxt.startsWith("skewX(")) {
                throw new UnsupportedOperationException("Transform:SkewX");
            } else if (transformTxt.startsWith("skewY(")) {
                throw new UnsupportedOperationException("Transform:SkewY");
            } else if (transformTxt.startsWith("matrix(")) {
                transformTxt = transformTxt.substring(7);
                StringTokenizer tokenizer2 = new StringTokenizer(transformTxt, " ");
                double mxx = Double.parseDouble(tokenizer2.nextToken());
                double myx = Double.parseDouble(tokenizer2.nextToken());
                double mxy = Double.parseDouble(tokenizer2.nextToken());
                double myy = Double.parseDouble(tokenizer2.nextToken());
                double tx = Double.parseDouble(tokenizer2.nextToken());
                double ty = Double.parseDouble(tokenizer2.nextToken());

                transform = Transform.affine(mxx, myx, mxy, myy, tx, ty);
            }
        }

        return transform;
    }

    private void setOpacity(Node node, StartElement element) {
        Attribute opacityAttribute = element.getAttributeByName(new QName("opacity"));
        if (opacityAttribute != null) {
            double opacity = Double.parseDouble(opacityAttribute.getValue());
            node.setOpacity(opacity);
        }
    }

    private Paint expressPaint(String value) {
        Paint paint = null;
        if (!value.equals("none")) {
            if (value.startsWith("url(#")) {
                String id = value.substring(5, value.length() - 1);
                paint = gradients.get(id);
            } else {
                paint = Color.web(value);
            }
        }

        return paint;
    }

    private void setShapeStyle(Shape shape, StartElement element) {
        Attribute fillAttribute = element.getAttributeByName(new QName("fill"));
        if (fillAttribute != null) {
            shape.setFill(expressPaint(fillAttribute.getValue()));
        }

        Attribute strokeAttribute = element.getAttributeByName(new QName("stroke"));
        if (strokeAttribute != null) {
            shape.setStroke(expressPaint(strokeAttribute.getValue()));
        }

        Attribute strokeWidthAttribute = element.getAttributeByName(new QName("stroke-width"));
        if (strokeWidthAttribute != null) {
            double strokeWidth = Double.parseDouble(strokeWidthAttribute.getValue());
            shape.setStrokeWidth(strokeWidth);
        }

        Attribute styleAttribute = element.getAttributeByName(new QName("style"));
        if (styleAttribute != null) {
            String styles = styleAttribute.getValue();
            StringTokenizer tokenizer = new StringTokenizer(styles, ";");
            while (tokenizer.hasMoreTokens()) {
                String style = tokenizer.nextToken();

                StringTokenizer tokenizer2 = new StringTokenizer(style, ":");
                String styleName = tokenizer2.nextToken();
                String styleValue = tokenizer2.nextToken();

                switch (styleName) {
                    case "fill":
                        shape.setFill(expressPaint(styleValue));
                        break;
                    case "stroke":
                        shape.setStroke(expressPaint(styleValue));
                        break;
                    case "stroke-width":
                        double strokeWidth = Double.parseDouble(styleValue);
                        shape.setStrokeWidth(strokeWidth);
                        break;
                    case "stroke-linecap":
                        StrokeLineCap linecap = StrokeLineCap.BUTT;
                        if (styleValue.equals("round")) {
                            linecap = StrokeLineCap.ROUND;
                        } else if (styleValue.equals("square")) {
                            linecap = StrokeLineCap.SQUARE;
                        } else if (!styleValue.equals("butt")) {
                            Logger.getLogger(SVGContentBuilder.class.getName()).log(Level.INFO, "No Support Style: {0} {1}", new Object[]{style, element});
                        }

                        shape.setStrokeLineCap(linecap);
                        break;
                    case "stroke-miterlimit":
                        double miterLimit = Double.parseDouble(styleValue);
                        shape.setStrokeMiterLimit(miterLimit);
                        break;
                    case "stroke-linejoin":
                        StrokeLineJoin linejoin = StrokeLineJoin.MITER;
                        if (styleValue.equals("bevel")) {
                            linejoin = StrokeLineJoin.BEVEL;
                        } else if (styleValue.equals("round")) {
                            linejoin = StrokeLineJoin.ROUND;
                        } else if (!styleValue.equals("miter")) {
                            Logger.getLogger(SVGContentBuilder.class.getName()).log(Level.INFO, "No Support Style: {0} {1}", new Object[]{style, element});
                        }

                        shape.setStrokeLineJoin(linejoin);
                        break;
                    case "opacity":
                        double opacity = Double.parseDouble(styleValue);
                        shape.setOpacity(opacity);
                        break;
                    default:
                        Logger.getLogger(SVGContentBuilder.class.getName()).log(Level.INFO, "No Support Style: {0} {1}", new Object[]{style, element});
                        break;
                }
            }
        }
    }
}

package org.basex.io.serial;

import org.basex.build.csv.*;
import org.basex.build.json.*;
import org.basex.core.*;
import org.basex.util.*;
import org.basex.util.options.*;

/**
 * This class defines all available serialization parameters.
 *
 * @author BaseX Team 2005-15, BSD License
 * @author Christian Gruen
 */
public final class SerializerOptions extends Options {
  /** Serialization parameter: yes/no. */
  public static final EnumOption<YesNo> BYTE_ORDER_MARK =
      new EnumOption<>("byte-order-mark", YesNo.NO);
  /** Serialization parameter: list of QNames. */
  public static final StringOption CDATA_SECTION_ELEMENTS =
      new StringOption("cdata-section-elements", "");
  /** Serialization parameter. */
  public static final StringOption DOCTYPE_PUBLIC =
      new StringOption("doctype-public", "");
  /** Serialization parameter. */
  public static final StringOption DOCTYPE_SYSTEM =
      new StringOption("doctype-system", "");
  /** Serialization parameter: valid encoding. */
  public static final StringOption ENCODING =
      new StringOption("encoding", Strings.UTF8);
  /** Serialization parameter: yes/no. */
  public static final EnumOption<YesNo> ESCAPE_URI_ATTRIBUTES =
      new EnumOption<>("escape-uri-attributes", YesNo.NO);
  /** Serialization parameter: yes/no. */
  public static final EnumOption<YesNo> INCLUDE_CONTENT_TYPE =
      new EnumOption<>("include-content-type", YesNo.NO);
  /** Serialization parameter: yes/no. */
  public static final EnumOption<YesNo> INDENT =
      new EnumOption<>("indent", YesNo.YES);
  /** Serialization parameter. */
  public static final StringOption SUPPRESS_INDENTATION =
      new StringOption("suppress-indentation", "");
  /** Serialization parameter. */
  public static final StringOption MEDIA_TYPE =
      new StringOption("media-type", "");
  /** Serialization parameter: xml/xhtml/html/text/json/csv/adaptive. */
  public static final EnumOption<SerialMethod> METHOD =
      new EnumOption<>("method", SerialMethod.class);
  /** Serialization parameter: NFC/NFD/NFKC/NKFD/fully-normalized/none. */
  public static final StringOption NORMALIZATION_FORM =
      new StringOption("normalization-form", Text.NONE);
  /** Serialization parameter: yes/no. */
  public static final EnumOption<YesNo> OMIT_XML_DECLARATION =
      new EnumOption<>("omit-xml-declaration", YesNo.YES);
  /** Serialization parameter: yes/no/omit. */
  public static final EnumOption<YesNoOmit> STANDALONE =
      new EnumOption<>("standalone", YesNoOmit.OMIT);
  /** Serialization parameter: yes/no. */
  public static final EnumOption<YesNo> UNDECLARE_PREFIXES =
      new EnumOption<>("undeclare-prefixes", YesNo.NO);
  /** Serialization parameter. */
  public static final StringOption USE_CHARACTER_MAPS =
      new StringOption("use-character-maps", "");
  /** Serialization parameter. */
  public static final StringOption ITEM_SEPARATOR =
      new StringOption("item-separator");
  /** Serialization parameter: 1.0/1.1. */
  public static final StringOption VERSION =
      new StringOption("version", "");
  /** Serialization parameter: 4.0/4.01/5.0. */
  public static final StringOption HTML_VERSION =
      new StringOption("html-version", "");
  /** Parameter document. */
  public static final StringOption PARAMETER_DOCUMENT =
      new StringOption("parameter-document", "");
  /** Serialization parameter: xml/xhtml/html/text. */
  public static final EnumOption<YesNo> ALLOW_DUPLICATE_NAMES =
      new EnumOption<>("allow-duplicate-names", YesNo.NO);
  /** Serialization parameter: xml/xhtml/html/text. */
  public static final EnumOption<SerialMethod> JSON_NODE_OUTPUT_METHOD =
      new EnumOption<>("json-node-output-method", SerialMethod.XML);

  /** Specific serialization parameter. */
  public static final OptionsOption<CsvOptions> CSV =
      new OptionsOption<>("csv", new CsvOptions());
  /** Specific serialization parameter. */
  public static final OptionsOption<JsonSerialOptions> JSON =
      new OptionsOption<>("json", new JsonSerialOptions());
  /** Specific serialization parameter: newline. */
  public static final EnumOption<Newline> NEWLINE =
      new EnumOption<>("newline",
        "\r".equals(Prop.NL) ? Newline.CR : "\n".equals(Prop.NL) ? Newline.NL : Newline.CRNL);
  /** Specific serialization parameter: indent with spaces or tabs. */
  public static final EnumOption<YesNo> TABULATOR =
      new EnumOption<>("tabulator", YesNo.NO);
  /** Specific serialization parameter: number of spaces to indent. */
  public static final NumberOption INDENTS =
      new NumberOption("indents", 2);
  /** Specific serialization parameter: maximum number of bytes to serialize. */
  public static final NumberOption LIMIT =
      new NumberOption("limit", -1);

  /** Static WebDAV character map. */
  public static final String WEBDAV = "webdav";

  /** Newlines. */
  public enum Newline {
    /** NL.   */ NL("\\n", "\n"),
    /** CR.   */ CR("\\r", "\r"),
    /** CRNL. */ CRNL("\\r\\n", "\r\n");

    /** Name. */
    private final String name;
    /** Newline. */
    private final String newline;

    /**
     * Constructor.
     * @param name name
     * @param newline newline string
     */
    Newline(final String name, final String newline) {
      this.name = name;
      this.newline = newline;
    }

    /**
     * Returns the newline string.
     * @return newline string
     */
    String newline() {
      return newline;
    }

    @Override
    public String toString() {
      return name;
    }
  }

  /** Serialization parameters (with and without indentation). */
  private static final SerializerOptions[] OPTIONS = new SerializerOptions[2];

  /**
   * Checks if the specified option is true.
   * @param option option
   * @return value
   */
  public boolean yes(final EnumOption<YesNo> option) {
    return get(option) == YesNo.YES;
  }

  /**
   * Returns serialization parameters.
   * @param indent indent XML
   * @return parameters
   */
  public static SerializerOptions get(final boolean indent) {
    SerializerOptions o = OPTIONS[indent ? 1 : 0];
    if(o == null) {
      o = new SerializerOptions();
      if(!indent) o.set(INDENT, YesNo.NO);
      OPTIONS[indent ? 1 : 0] = o;
    }
    return o;
  }

  /**
   * Default constructor.
   */
  public SerializerOptions() {
  }

  /**
   * Constructor with options to be copied.
   * @param opts options
   */
  public SerializerOptions(final SerializerOptions opts) {
    super(opts);
  }
}

/**
 * generated by Xtext
 */
package m.yaml.impl;

import m.yaml.Document;
import m.yaml.Element;
import m.yaml.File;
import m.yaml.Guid;
import m.yaml.InlineList;
import m.yaml.InlineMap;
import m.yaml.InlineSemicolonList;
import m.yaml.KeyValue;
import m.yaml.KeyValueList;
import m.yaml.Map;
import m.yaml.Node;
import m.yaml.Tag;
import m.yaml.Version;
import m.yaml.Word;
import m.yaml.YamlFactory;
import m.yaml.YamlPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class YamlFactoryImpl extends EFactoryImpl implements YamlFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static YamlFactory init()
  {
    try
    {
      YamlFactory theYamlFactory = (YamlFactory)EPackage.Registry.INSTANCE.getEFactory(YamlPackage.eNS_URI);
      if (theYamlFactory != null)
      {
        return theYamlFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new YamlFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YamlFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case YamlPackage.FILE: return createFile();
      case YamlPackage.NODE: return createNode();
      case YamlPackage.DOCUMENT: return createDocument();
      case YamlPackage.KEY_VALUE: return createKeyValue();
      case YamlPackage.MAP: return createMap();
      case YamlPackage.KEY_VALUE_LIST: return createKeyValueList();
      case YamlPackage.ELEMENT: return createElement();
      case YamlPackage.INLINE_LIST: return createInlineList();
      case YamlPackage.INLINE_MAP: return createInlineMap();
      case YamlPackage.INLINE_SEMICOLON_LIST: return createInlineSemicolonList();
      case YamlPackage.WORD: return createWord();
      case YamlPackage.FLOAT: return createFloat();
      case YamlPackage.LONG: return createLong();
      case YamlPackage.GUID: return createGuid();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case YamlPackage.VERSION:
        return createVersionFromString(eDataType, initialValue);
      case YamlPackage.TAG:
        return createTagFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case YamlPackage.VERSION:
        return convertVersionToString(eDataType, instanceValue);
      case YamlPackage.TAG:
        return convertTagToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public File createFile()
  {
    FileImpl file = new FileImpl();
    return file;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Node createNode()
  {
    NodeImpl node = new NodeImpl();
    return node;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Document createDocument()
  {
    DocumentImpl document = new DocumentImpl();
    return document;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KeyValue createKeyValue()
  {
    KeyValueImpl keyValue = new KeyValueImpl();
    return keyValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map createMap()
  {
    MapImpl map = new MapImpl();
    return map;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KeyValueList createKeyValueList()
  {
    KeyValueListImpl keyValueList = new KeyValueListImpl();
    return keyValueList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Element createElement()
  {
    ElementImpl element = new ElementImpl();
    return element;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InlineList createInlineList()
  {
    InlineListImpl inlineList = new InlineListImpl();
    return inlineList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InlineMap createInlineMap()
  {
    InlineMapImpl inlineMap = new InlineMapImpl();
    return inlineMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InlineSemicolonList createInlineSemicolonList()
  {
    InlineSemicolonListImpl inlineSemicolonList = new InlineSemicolonListImpl();
    return inlineSemicolonList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Word createWord()
  {
    WordImpl word = new WordImpl();
    return word;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public m.yaml.Float createFloat()
  {
    FloatImpl float_ = new FloatImpl();
    return float_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public m.yaml.Long createLong()
  {
    LongImpl long_ = new LongImpl();
    return long_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Guid createGuid()
  {
    GuidImpl guid = new GuidImpl();
    return guid;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Version createVersionFromString(EDataType eDataType, String initialValue)
  {
    Version result = Version.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertVersionToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Tag createTagFromString(EDataType eDataType, String initialValue)
  {
    Tag result = Tag.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertTagToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public YamlPackage getYamlPackage()
  {
    return (YamlPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static YamlPackage getPackage()
  {
    return YamlPackage.eINSTANCE;
  }

} //YamlFactoryImpl

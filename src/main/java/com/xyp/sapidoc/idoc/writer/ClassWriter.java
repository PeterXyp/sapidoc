package com.xyp.sapidoc.idoc.writer;

import com.xyp.sapidoc.idoc.attribute.IdocFieldProperty;
import com.xyp.sapidoc.idoc.util.ContentUtil;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Yunpeng_Xu
 */
public class ClassWriter {

    private String className;
    private String pkgName;
    private String targetDir;
    private List<IdocFieldProperty> fields = new ArrayList<IdocFieldProperty>();
    private Set<String> imports = new HashSet<String>();
    private List<String> annotations = new ArrayList<String>();

    public ClassWriter(String className, String pkgName, String targetDir) {
        this.className = className;
        this.pkgName = pkgName;
        this.targetDir = targetDir;
    }

    public void writerClass() throws IOException {
        String packageDir = pkgName.replaceAll("[.]", "/");
        BufferedWriter writer = new BufferedWriter(new FileWriter(targetDir + packageDir + "/" + className + ".java"));
        writer.write(generatePkg());
        writer.write(generateImport());
        writer.write(generateBeginClass());
        writer.write(generateFields());
        writer.write(generateEndClass());
        writer.flush();
        writer.close();
    }

    private String generatePkg() {
        return "package " + pkgName + ";" + ContentUtil.appendCRLF(2);
    }

    private String generateImport() {
        StringBuilder iptsStr = new StringBuilder();
        for (String ipt : imports) {
            iptsStr.append("import ").append(ipt).append(";").append(ContentUtil.appendCRLF(1));
        }
        iptsStr.append(ContentUtil.appendCRLF(1));
        return iptsStr.toString();
    }

    private String generateBeginClass() {
        StringBuilder sb = new StringBuilder();
        //add class annotation
        for (String annotation : annotations) {
            sb.append(annotation).append(ContentUtil.appendCRLF(1));
        }
        //public class EDI_DC40 {
        sb.append("public class ").append(getClassName()).append(" {").append(ContentUtil.appendCRLF(2));
        return sb.toString();
    }

    private String generateFields() {
        StringBuilder sb = new StringBuilder();
        //field declaration
        for (IdocFieldProperty field : fields) {
            sb.append(field.getFieldDeclaration());
        }
        //field setter
        for (IdocFieldProperty field : fields) {
            sb.append(field.getSetter());
        }
        //field getter
        for (IdocFieldProperty field : fields) {
            sb.append(field.getGetter());
        }
        return sb.toString();
    }

    private String generateEndClass() {
        StringBuilder sb = new StringBuilder();
        sb.append(ContentUtil.appendCRLF(1))
                .append("}");
        return sb.toString();
    }

    public void addField(IdocFieldProperty field) {
        fields.add(field);
    }

    public void addFields(List<IdocFieldProperty> fields) {
        for (IdocFieldProperty field : fields) {
            addField(field);
        }
    }

    public void addImport(String ipt) {
        imports.add(ipt);
    }

    public void addImports(Set<String> imports) {
        for (String ipt : imports) {
            addImport(ipt);
        }
    }

    public void addAnnotation(Class<? extends Annotation> annotation) {
        annotations.add("@" + annotation.getSimpleName());
        addImport(annotation.getName());
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }

    public String getClassName() {
        return className;
    }

    public String getPkgName() {
        return pkgName;
    }

    public String getTargetDir() {
        return targetDir;
    }

    public List<IdocFieldProperty> getFields() {
        return fields;
    }

    public Set<String> getImports() {
        return imports;
    }

    public List<String> getAnnotations() {
        return annotations;
    }
}

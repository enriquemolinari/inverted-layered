package radio;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

public class TestArchitecture {

 private static final String PKG_PERSISTENCE = "radio.persistence";
 private static final String PKG_MODEL = "radio.model";
 private static final String PKG_MODEL_API = "radio.model.api";
 private static final String PKG_UI = "radio.ui";
 private static final String ROOT_PKG = "radio";

 @Test
 public void testLayers() {
  layeredArchitecture()
    .layer("UI").definedBy(PKG_UI)
    .layer("BusinessLogicAPI").definedBy(PKG_MODEL_API)
    .layer("BusinessLogic").definedBy(PKG_MODEL)
    .layer("Persistence").definedBy(PKG_PERSISTENCE)
    .whereLayer("UI").mayNotBeAccessedByAnyLayer()
    .whereLayer("BusinessLogic").mayNotBeAccessedByAnyLayer()
    .whereLayer("BusinessLogicAPI").mayOnlyBeAccessedByLayers("BusinessLogic", "UI", "Persistence")
    .whereLayer("Persistence").mayNotBeAccessedByAnyLayer()
    .check(new ClassFileImporter().importPackages(ROOT_PKG));
 }
 
// @Test This one does not work, looks like an ArchUnit bug
 public void modelShouldOnlyDependOnModelOrJdk() {
  JavaClasses jc = new ClassFileImporter().importPackages(ROOT_PKG);

  ArchRule r1 = classes().that().resideInAPackage(PKG_MODEL).should()
    .onlyDependOnClassesThat().resideInAnyPackage(PKG_MODEL,
      PKG_MODEL_API, "java..");
  r1.check(jc);
 }

 @Test
 public void portsShouldOnlyDependOnPortsOrJdk() {
  JavaClasses jc = new ClassFileImporter().importPackages(ROOT_PKG);

  ArchRule r1 = classes().that().resideInAPackage(PKG_MODEL_API).should()
    .onlyDependOnClassesThat()
    .resideInAnyPackage(PKG_MODEL_API, "java..");
  r1.check(jc);
 }

 @Test
 public void persistenceShouldOnlyDependOnModelAPI() {
  JavaClasses jc = new ClassFileImporter().importPackages(ROOT_PKG);

  ArchRule r1 = classes().that().resideInAPackage(PKG_PERSISTENCE).should()
    .onlyDependOnClassesThat()
    .resideInAnyPackage(PKG_PERSISTENCE, PKG_MODEL_API, "java..", "javax..");
  r1.check(jc);
 }

 @Test
 public void uiShouldOnlyDependOnModelAPI() {
  JavaClasses jc = new ClassFileImporter().importPackages(ROOT_PKG);

  ArchRule r1 = classes().that().resideInAPackage(PKG_UI).should()
    .onlyDependOnClassesThat()
    .resideInAnyPackage(PKG_UI, PKG_MODEL_API, "java..", "javax..");
  r1.check(jc);
 }
}

package org.checkerframework.checker.crypto;

import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.MethodInvocationTree;
import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import org.checkerframework.checker.compilermsgs.qual.CompilerMessageKey;
import org.checkerframework.checker.crypto.qual.Unique;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.common.basetype.BaseTypeVisitor;
import org.checkerframework.framework.type.AnnotatedTypeMirror;
import org.checkerframework.javacutil.AnnotationBuilder;
import org.checkerframework.javacutil.AnnotationUtils;

public class CryptoVisitor extends BaseTypeVisitor<CryptoAnnotatedTypeFactory> {

    final boolean STRONG_BOX_BACKED_ENABLE = checker.getLintOption("strongboxbacked", false);

    final ProcessingEnvironment env;

    public CryptoVisitor(final BaseTypeChecker checker) {
        super(checker);
        env = checker.getProcessingEnvironment();
    }

    protected final AnnotationMirror UNIQUE = AnnotationBuilder.fromClass(elements, Unique.class);

    @Override
    public Void visitMethodInvocation(MethodInvocationTree node, Void p) {
        List<? extends ExpressionTree> valueExp = node.getArguments();
        return super.visitMethodInvocation(node, p);
    }

    @Override
    protected void commonAssignmentCheck(
            AnnotatedTypeMirror varType,
            ExpressionTree valueExp,
            @CompilerMessageKey String errorKey,
            Object... extraArgs) {
        // forbid @unique alias here
        AnnotatedTypeMirror valueType = atypeFactory.getAnnotatedType(valueExp);
        if (AnnotationUtils.areSameByName((AnnotationMirror) valueType, UNIQUE)) {
            checker.reportError(valueExp, "unique.alias.not.allowed");
        }
        super.commonAssignmentCheck(varType, valueExp, errorKey, extraArgs);
        return;
    }
}

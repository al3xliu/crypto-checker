package org.checkerframework.checker.crypto;

import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.MethodInvocationTree;
import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import org.checkerframework.checker.compilermsgs.qual.CompilerMessageKey;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.common.basetype.BaseTypeVisitor;
import org.checkerframework.framework.type.AnnotatedTypeMirror;

public class CryptoVisitor extends BaseTypeVisitor<CryptoAnnotatedTypeFactory> {

    final boolean STRONG_BOX_BACKED_ENABLE = checker.getLintOption("strongboxbacked", false);

    final ProcessingEnvironment env;

    public CryptoVisitor(final BaseTypeChecker checker) {
        super(checker);
        env = checker.getProcessingEnvironment();
    }

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
        super.commonAssignmentCheck(varType, valueExp, errorKey, extraArgs);
    }
}

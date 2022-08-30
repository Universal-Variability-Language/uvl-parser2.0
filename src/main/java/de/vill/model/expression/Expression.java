package de.vill.model.expression;

import de.vill.model.Feature;

import java.util.List;
import java.util.Set;

public abstract class Expression {
    public String toString(){
        return toString(true, "");
    }

    public abstract String toString(boolean withSubmodels, String currentAlias);

    public abstract List<Expression> getExpressionSubParts();

    public abstract void replaceExpressionSubPart(Expression oldSubExpression, Expression newSubExpression);

    public abstract double evaluate(Set<Feature> selectedFeatures);
}

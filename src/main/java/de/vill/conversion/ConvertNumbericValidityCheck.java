package de.vill.conversion;

import de.vill.model.Attribute;
import de.vill.model.Feature;
import de.vill.model.FeatureModel;
import de.vill.model.Group;
import de.vill.model.LanguageLevel;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ConvertNumbericValidityCheck implements IConversionStrategy {
    @Override
    public Set<LanguageLevel> getLevelsToBeRemoved() {
        return new HashSet<>(Collections.singletonList(LanguageLevel.NUMERIC_VALIDITY_CHECK));
    }

    @Override
    public Set<LanguageLevel> getTargetLevelsOfConversion() {
        return new HashSet<>(Collections.singletonList(LanguageLevel.TYPE_LEVEL));
    }

    //TODO
    @Override
    public void convertFeatureModel(final FeatureModel rootFeatureModel, final FeatureModel featureModel) {
        this.traverseFeatures(featureModel.getRootFeature());
    }

    private void traverseFeatures(final Feature feature) {
        if (feature.getFeatureType() != null) {
            feature.getAttributes().put(
                    "feature_type",
                    new Attribute<>("feature_type", feature.getFeatureType().getName())
            );
        }

        for (final Group group : feature.getChildren()) {
            for (final Feature subFeature : group.getFeatures()) {
                this.traverseFeatures(subFeature);
            }
        }
    }
}

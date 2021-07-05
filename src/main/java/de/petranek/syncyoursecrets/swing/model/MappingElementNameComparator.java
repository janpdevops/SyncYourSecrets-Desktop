package de.petranek.syncyoursecrets.swing.model;

import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

import de.petranek.syncyoursecrets.xmlmapping.MappingElement;

public class MappingElementNameComparator implements Comparator<MappingElement> {

	@Override
	public int compare(MappingElement o1, MappingElement o2) {

		if (o1 == null) {
			if (o2 == null) {
				return 0;
			} else {
				return -1;
			}

		}

		if (o2 == null) {
			return 1;
		}

		return StringUtils.compareIgnoreCase(o1.getName(), o2.getName());

	}

}

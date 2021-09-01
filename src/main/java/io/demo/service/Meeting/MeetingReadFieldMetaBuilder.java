package io.demo.service.Meeting;

import io.demo.controller.TeslerRestController;
import io.demo.dto.MeetingDTO;
import io.demo.dto.MeetingDTO_;
import io.tesler.core.crudma.bc.impl.InnerBcDescription;
import io.tesler.core.dto.DrillDownType;
import io.tesler.core.dto.rowmeta.FieldsMeta;
import io.tesler.core.dto.rowmeta.RowDependentFieldsMeta;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import org.springframework.stereotype.Service;

@Service
public class MeetingReadFieldMetaBuilder extends FieldMetaBuilder<MeetingDTO> {

	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<MeetingDTO> fields, InnerBcDescription bcDescription,
			Long id, Long parentId) {
		fields.setDrilldown(
				MeetingDTO_.id,
				DrillDownType.INNER,
				"/screen/meeting/view/meetingview/" + TeslerRestController.meeting + "/" + id
		);
		fields.setDrilldown(
				MeetingDTO_.clientName,
				DrillDownType.INNER,
				"/screen/client/view/clientview/" + TeslerRestController.client + "/" +
						fields.get(MeetingDTO_.client_id).getCurrentValue()
		);
	}

	@Override
	public void buildIndependentMeta(FieldsMeta<MeetingDTO> fields, InnerBcDescription bcDescription,
			Long parentId) {

	}

}

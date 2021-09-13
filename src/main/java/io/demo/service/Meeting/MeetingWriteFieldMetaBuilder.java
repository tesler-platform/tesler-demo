package io.demo.service.Meeting;

import io.demo.dto.MeetingDTO;
import io.demo.dto.MeetingDTO_;
import io.demo.entity.enums.MeetingStatus;
import io.tesler.core.crudma.bc.impl.InnerBcDescription;
import io.tesler.core.dto.rowmeta.FieldsMeta;
import io.tesler.core.dto.rowmeta.RowDependentFieldsMeta;
import io.tesler.core.service.rowmeta.FieldMetaBuilder;
import org.springframework.stereotype.Service;

@Service
public class MeetingWriteFieldMetaBuilder extends FieldMetaBuilder<MeetingDTO> {

	@Override
	public void buildRowDependentMeta(RowDependentFieldsMeta<MeetingDTO> fields, InnerBcDescription bcDescription, Long id, Long parentId) {
		fields.setEnabled(
				MeetingDTO_.agenda,
				MeetingDTO_.startDateTime,
				MeetingDTO_.endDateTime,
				MeetingDTO_.address,
				MeetingDTO_.responsibleName,
				MeetingDTO_.clientName,
				MeetingDTO_.client_id
		);
		fields.setRequired(
				MeetingDTO_.agenda,
				MeetingDTO_.startDateTime,
				MeetingDTO_.endDateTime,
				MeetingDTO_.address
		);
		if(fields.get(MeetingDTO_.status).getCurrentValue() == MeetingStatus.Completed){
			fields.setEnabled(
					MeetingDTO_.notes,
					MeetingDTO_.result
					);
		}
		if(fields.get(MeetingDTO_.client_id).getCurrentValue() != null){
			fields.setEnabled(
					MeetingDTO_.contactName
			);
		}
	}

	@Override
	public void buildIndependentMeta(FieldsMeta<MeetingDTO> fields, InnerBcDescription bcDescription, Long parentId) {
		fields.setForceActive(MeetingDTO_.client_id);
		fields.setForceActive(MeetingDTO_.startDateTime);
		fields.setForceActive(MeetingDTO_.endDateTime);
	}
}

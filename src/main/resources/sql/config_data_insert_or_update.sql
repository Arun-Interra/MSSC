MERGE INTO MMAT.SSC_CONFIG_DATA AS tab 
USING (VALUES
        ( ?,?,?,?,?,?,?,NOW(),? ,NOW())
    ) AS merge (TYPE , SUB_TYPE, KEY_CD , VALUE, DESCRIPTION, STATUS, CREATED_BY, CREATE_TM, LAST_UPDATED_BY, LAST_UPDATE_TM)
    ON tab.TYPE = merge.TYPE
    WHEN MATCHED THEN
        UPDATE SET tab.TYPE = merge.TYPE,
                   tab.SUB_TYPE = merge.SUB_TYPE,
                   tab.KEY_CD = merge.KEY_CD,
                   tab.VALUE = merge.VALUE,
                   tab.DESCRIPTION = merge.DESCRIPTION,
                   tab.STATUS = merge.STATUS,
                   tab.CREATED_BY = merge.CREATED_BY,
                   tab.CREATE_TM = merge.CREATE_TM,
                   tab.LAST_UPDATED_BY = merge.LAST_UPDATED_BY,
                   tab.LAST_UPDATE_TM = merge.LAST_UPDATE_TM
    WHEN NOT MATCHED THEN
        INSERT (TYPE , SUB_TYPE, KEY_CD , VALUE, DESCRIPTION, STATUS, CREATED_BY, CREATE_TM, LAST_UPDATED_BY, LAST_UPDATE_TM)
        VALUES (merge.TYPE,merge.SUB_TYPE,merge.KEY_CD,merge.VALUE,merge.DESCRIPTION,merge.STATUS, merge.CREATED_BY,
                NOW(),merge.LAST_UPDATED_BY,NOW())
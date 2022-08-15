MERGE INTO MMAT.SSC_FACILITY_DATA AS tab 
USING (VALUES
        ( ?,?,?,?,?,?,?,? ,NOW(),? ,NOW())
    ) AS merge (YEAR, MONTH, DLR_CD , STALLS_CNT, MMA_STALLS_CNT, LIFTS_CNT, MMA_LIFTS_CNT, CREATED_BY, CREATE_TM, LAST_UPDATED_BY, LAST_UPDATE_TM)
    ON tab.DLR_CD = merge.DLR_CD
    WHEN MATCHED THEN
        UPDATE SET tab.YEAR = merge.YEAR,
                   tab.MONTH = merge.MONTH,
                   tab.DLR_CD = merge.DLR_CD,
                   tab.STALLS_CNT = merge.STALLS_CNT,
                   tab.MMA_STALLS_CNT = merge.MMA_STALLS_CNT,
                   tab.LIFTS_CNT = merge.LIFTS_CNT,
                   tab.MMA_LIFTS_CNT = merge.MMA_LIFTS_CNT,
                   tab.CREATED_BY = merge.CREATED_BY,
                   tab.CREATE_TM = merge.CREATE_TM,
                   tab.LAST_UPDATED_BY = merge.LAST_UPDATED_BY,
                   tab.LAST_UPDATE_TM = merge.LAST_UPDATE_TM
    WHEN NOT MATCHED THEN
        INSERT (YEAR, MONTH, DLR_CD , STALLS_CNT, MMA_STALLS_CNT, LIFTS_CNT, MMA_LIFTS_CNT, CREATED_BY, CREATE_TM, LAST_UPDATED_BY, LAST_UPDATE_TM)
        VALUES (merge.YEAR,merge.MONTH,merge.DLR_CD,merge.STALLS_CNT,merge.MMA_STALLS_CNT,merge.LIFTS_CNT,merge.MMA_LIFTS_CNT,merge.CREATED_BY,
                NOW(),merge.LAST_UPDATED_BY,NOW())

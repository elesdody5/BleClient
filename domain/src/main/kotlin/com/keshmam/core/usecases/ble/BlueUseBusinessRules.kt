package com.keshmam.core.usecases.ble

import com.keshmam.core.entities.BusinessRules
import com.keshmam.core.entities.ble.BleScannerStateDomainEntity
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import java.util.*


/**
 * scan  to get ble device
 * @return flow<[BleScannerStateDomainEntity]>
 */
interface SearchBLEDeviceRule {
   @BusinessRules
   operator fun invoke(
      serviceUUId: UUID,
   ): Flow<BleScannerStateDomainEntity>
}


/**
 * open device bluetooth
 */
interface OpenBlueToothRule {
   @BusinessRules
   operator fun invoke() : Result<Nothing?>
}


/**
 * request ble permission
 */
interface RequestBlePermissionRule<T:Any>{
   @BusinessRules
   operator fun invoke(
      blePermissionsRequester : T,
      onPermissionsDenial: () -> Unit,
      onPermissionsGranted : ()->Unit
   )

}
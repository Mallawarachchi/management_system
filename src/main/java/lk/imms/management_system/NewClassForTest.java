package lk.imms.management_system;

import lk.imms.management_system.asset.petition.entity.Enum.PetitionerType;
import lk.imms.management_system.asset.petitioner.service.PetitionerService;

public class NewClassForTest {
    private static   PetitionerService petitionerService;

    public static void main(String[] args) {


      /*  System.out.println(LocalDateTime.now());

        HashMap<String,String> n = new HashMap<>();
        n.put("CD", "Kumara");
        n.put("KD", "Asanka");
        n.put("BD", "Saman");
        n.put("CD", "Kamal");
        System.out.println(n);*/
      String stringValue = "PRESIDENT";
        System.out.println(petitionerService.findByPetitionerType(PetitionerType.valueOf(stringValue)));

    }
}

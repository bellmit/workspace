import countryGeo from '@/data/countryGeo.json'
import provinceGeo from '@/data/provinceGeo.json'
import cityGeo from '@/data/cityGeo.json'

export default { ...countryGeo,
  ...provinceGeo,
  ...cityGeo
}

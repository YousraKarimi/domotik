import logging

# Configurer la journalisation
logging.basicConfig(level=logging.DEBUG, format='%(asctime)s - %(name)s - %(levelname)s - %(message)s')

logging.getLogger('pika').setLevel(logging.WARNING)
logging.getLogger('redis').setLevel(logging.WARNING)

logger = logging.getLogger(__name__)

